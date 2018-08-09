package nearsoft.skytouch.microservice.service;

import nearsoft.skytouch.common.model.Product;

import java.util.List;

public interface ProductService {
    void storeProduct(Product product);

    List<Product> getProducts();
}
