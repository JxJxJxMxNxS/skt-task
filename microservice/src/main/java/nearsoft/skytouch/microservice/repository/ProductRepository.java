package nearsoft.skytouch.microservice.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository {
    void storeProduct(String productJSON);

    void getProducts();
}
