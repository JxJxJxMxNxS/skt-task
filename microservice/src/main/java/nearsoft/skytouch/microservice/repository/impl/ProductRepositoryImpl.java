package nearsoft.skytouch.microservice.repository.impl;

import nearsoft.skytouch.common.model.Product;
import nearsoft.skytouch.microservice.repository.ProductRepository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {

    @PersistenceContext
    private EntityManager entityManager;

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

        List<Product> products = query.getResultList();

        return products.get(0);
    }

    @Override
    public List<Product> getProducts() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("getproducts");
        query.registerStoredProcedureParameter(1, void.class, ParameterMode.REF_CURSOR); //refcursor

        return query.getResultList();
    }
}
