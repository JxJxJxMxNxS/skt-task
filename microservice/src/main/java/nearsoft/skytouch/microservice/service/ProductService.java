package nearsoft.skytouch.microservice.service;

import nearsoft.skytouch.common.model.Product;

import java.util.List;

public interface ProductService {
    Product storeProduct(Product product) throws Exception;

    List<Product> getProducts() throws Exception;
}
