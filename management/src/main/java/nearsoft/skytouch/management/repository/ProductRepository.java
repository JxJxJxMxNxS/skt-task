package nearsoft.skytouch.management.repository;

import nearsoft.skytouch.common.model.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> retrieveProducts();
}
