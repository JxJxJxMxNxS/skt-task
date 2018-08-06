package nearsoft.skytouch.microservice.repository;

import nearsoft.skytouch.common.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository {
    Product storeProduct(Product product);
    List<Product> getProducts();
}
