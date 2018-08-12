package nearsoft.skytouch.management.repository.Impl;

import nearsoft.skytouch.common.ProductJSONSerializer;
import nearsoft.skytouch.common.model.Product;
import nearsoft.skytouch.management.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
class ProductRepositoryImpl implements ProductRepository {

    private final static Logger LOGGER = LoggerFactory.getLogger(ProductRepositoryImpl.class);
    private ProductJSONSerializer productJSONSerializer;
    private RabbitTemplate rabbitTemplate;
    private DirectExchange directExchange;


    public ProductRepositoryImpl(ProductJSONSerializer productJSONSerializer, RabbitTemplate rabbitTemplate, DirectExchange directExchange) {
        this.productJSONSerializer = productJSONSerializer;
        this.rabbitTemplate = rabbitTemplate;
        this.directExchange = directExchange;
    }

    public List<Product> retrieveProducts() {
        Object o = rabbitTemplate.convertSendAndReceive(directExchange.getName(), "request.products", "request");
        return productJSONSerializer.deserializeList(o.toString());
    }

    @Override
    public Product storeProduct(Product product) {
        Object o = rabbitTemplate.convertSendAndReceive(directExchange.getName(), "store.product", productJSONSerializer.serializeObject(product));
        return productJSONSerializer.deserializeObject(o.toString());
    }

}
