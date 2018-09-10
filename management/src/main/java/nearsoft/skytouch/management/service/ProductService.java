package nearsoft.skytouch.management.service;

import nearsoft.skytouch.common.model.Product;

import java.io.IOException;
import java.util.List;

/**
 * Interface to define the methods that should be in the implementation to interact with the
 * products it can be the way in which the products are retrieved or stored
 */
public interface ProductService {
    /**
     * Returns all the products
     *
     * @return a list with all the products
     */
    List<Product> retrieveProducts() throws IOException;

    /***
     * Service to store the products
     * @param product the product to be stored
     * @return the stored product
     */
    Product storeProduct(Product product) throws IOException;
}
