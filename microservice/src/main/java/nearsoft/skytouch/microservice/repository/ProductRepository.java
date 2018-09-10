package nearsoft.skytouch.microservice.repository;

import nearsoft.skytouch.common.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface to define the methods that should be in the implementation to access the product data
 * it can be the way in which the products are retrieved sored or searched
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /***
     * Mechanism to store a product in the database
     * @param name : the name of the product
     * @param description : the product description
     * @param price : the price of the product
     * @return the ID of the stored product
     */
    @Procedure()
    Long addProduct(String name, String description, Long price);

    /**
     * Returns all the products
     *
     * @return a list with all the products
     */
    @Query(nativeQuery = true, value = "select * from getproducts()")
    List<Product> getproducts();
}
