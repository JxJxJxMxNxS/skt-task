package nearsoft.skytouch.microservice.repository;


import nearsoft.skytouch.common.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;


    @Test
    public void storeProduct() {
        Product product = new Product();
        product.setName("Name Test1");
        product.setDescription("Description Test1");
        product.setPrice(10L);

        Long storedProductId = productRepository.addProduct(product.getName(), product.getDescription(), product.getPrice());

        assertThat(storedProductId).isNotNull();
        assertThat(storedProductId).isNotZero();
        assertThat(storedProductId).isNotNull();
    }

    @Test
    public void returnProductsList() {
        Product product1 = new Product();
        product1.setName("Product test 1");
        product1.setDescription("Product description 1");
        product1.setPrice(10L);

        Product product2 = new Product();
        product2.setName("Product test 2");
        product2.setDescription("Product description 2");
        product2.setPrice(20L);

        product1.setId(productRepository.addProduct(product1.getName(), product1.getDescription(), product1.getPrice()));
        product2.setId(productRepository.addProduct(product2.getName(), product2.getDescription(), product2.getPrice()));

        List<Product> products = productRepository.getproducts();
        assertThat(products).hasSize(2);
        assertThat(products.get(0).getName()).matches(product1.getName());
        assertThat(products.get(1).getName()).matches(product2.getName());
    }
}
