package nearsoft.skytouch.management.repository.Impl;

import nearsoft.skytouch.common.model.Product;
import nearsoft.skytouch.management.repository.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    public List<Product> retrieveProducts() {
        Product product;
        List<Product> products = new ArrayList<>();
        product = new Product();
        product.setName("Sabritas");
        product.setDescription("Saben bien");
        product.setPrice(20L);
        products.add(product);
        product = new Product();
        product.setName("Coca");
        product.setDescription("rico");
        product.setPrice(10L);
        products.add(product);
        product = new Product();
        product.setName("Gansito");
        product.setDescription("deliciosos cuando estan congelados");
        product.setPrice(12L);
        products.add(product);
        return products;
    }
}
