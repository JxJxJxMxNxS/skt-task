package nearsoft.skytouch.microservice.repository.impl;

import nearsoft.skytouch.common.ProductJSONSerializer;
import nearsoft.skytouch.common.config.RabbitMQConfiguration;
import nearsoft.skytouch.common.model.Product;
import nearsoft.skytouch.microservice.channel.ProductChannel;
import nearsoft.skytouch.microservice.repository.ProductRepository;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;

@EnableBinding(ProductChannel.class)
@Repository
public class ProductRepositoryImpl implements ProductRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private ProductChannel productChannel;
    private ProductJSONSerializer productJSONSerializer;

    public ProductRepositoryImpl(EntityManager entityManager, ProductChannel productChannel, ProductJSONSerializer productJSONSerializer) {
        this.entityManager = entityManager;
        this.productChannel = productChannel;
        this.productJSONSerializer = productJSONSerializer;
    }

    @StreamListener(RabbitMQConfiguration.CREATE_PRODUCTS_CHANNEL_NAME)
    @Transactional
    @Override
    public void storeProduct(String productJSON) {

        Product product = productJSONSerializer.deserializeObject(productJSON);
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("addproduct");

        query.registerStoredProcedureParameter(1, void.class, ParameterMode.REF_CURSOR); //refcursor
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN); //name
        query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN); //description
        query.registerStoredProcedureParameter(4, Long.class, ParameterMode.IN);// price

        query.setParameter(2, product.getName());
        query.setParameter(3, product.getDescription());
        query.setParameter(4, product.getPrice());

        List<Object[]> products = query.getResultList();
        Product product1 = new Product();
        product1.setName(products.get(0)[0].toString());
        product1.setDescription(products.get(0)[1].toString());
        product1.setPrice(Long.parseLong(products.get(0)[2].toString()));
        product1.setId(Long.parseLong(products.get(0)[3].toString()));
    }

    @StreamListener(RabbitMQConfiguration.REQUEST_PRODUCTS_CHANNEL_NAME)
    @Transactional
    @Override
    public void getProducts() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("getproducts");
        query.registerStoredProcedureParameter(1, void.class, ParameterMode.REF_CURSOR); //refcursor
        List<Object[]> productsRaw = query.getResultList();
        List<Product> products = new LinkedList<>();
        Product product;
        for (Object[] productRaw : productsRaw) {
            product = new Product();
            product.setName(productRaw[0].toString());
            product.setDescription(productRaw[1].toString());
            product.setPrice(Long.parseLong(productRaw[2].toString()));
            product.setId(Long.parseLong(productRaw[3].toString()));
            products.add(product);
        }

        productChannel.sendProducts().send(MessageBuilder.withPayload(productJSONSerializer.serializeList(products)).build());
    }

}
