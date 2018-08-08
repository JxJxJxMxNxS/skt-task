package nearsoft.skytouch.microservice.repository.impl;

import nearsoft.skytouch.common.model.Product;
import nearsoft.skytouch.microservice.repository.ProductRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public Product storeProduct(Product product) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("addproduct");

        query.registerStoredProcedureParameter(1, void.class, ParameterMode.REF_CURSOR); //refcursor
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN); //name
        query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN); //description
        query.registerStoredProcedureParameter(4, Long.class, ParameterMode.IN);// price

        query.setParameter(2, product.getName());
        query.setParameter(3, product.getDescription());
        query.setParameter(4, product.getPrice());

        List<Object[]> products = query.getResultList();
        Product p = new Product();
        p.setName(products.get(0)[0].toString());
        p.setDescription(products.get(0)[1].toString());
        p.setPrice(Long.parseLong(products.get(0)[2].toString()));
        p.setId(Long.parseLong(products.get(0)[3].toString()));
        return p;
    }

    @Transactional
    @Override
    public List<Product> getProducts() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("getproducts");
        query.registerStoredProcedureParameter(1, void.class, ParameterMode.REF_CURSOR); //refcursor
        List<Object[]>ps=query.getResultList();
        List<Product> products = new LinkedList<>();
        Product p;
        for (Object[] product:
             ps) {
            p = new Product();
            p.setName(product[0].toString());
            p.setDescription(product[1].toString());
            p.setPrice(Long.parseLong(product[2].toString()));
            p.setId(Long.parseLong(product[3].toString()));
            ((LinkedList<Product>) products).push(p);
        }


        return products;
    }
}
