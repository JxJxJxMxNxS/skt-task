package nearsoft.skytouch.task.repository;

import nearsoft.skytouch.task.model.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> retrieveProducts();
}
