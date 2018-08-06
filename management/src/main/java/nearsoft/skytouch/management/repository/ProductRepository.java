package nearsoft.skytouch.management.repository;

import nearsoft.skytouch.common.model.Product;

import java.util.List;

/**
 * Interface to define the methods that should be in the implementation to access the product data
 * it can be the way in which the products are retrieved sored or searched
 */

public interface ProductRepository {
    /**
     * Returns all the products
     *
     * @return a list with all the products
     */
    List<Product> retrieveProducts();

    /***
     * Mechanism to store the products
     * @param product the product to be stored
     * @return the stored product
     */
    Product storeProduct(Product product);
}
