package nearsoft.skytouch.management.repository;

import nearsoft.skytouch.common.ProductJSONSerializer;
import nearsoft.skytouch.common.model.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductRepositoryTest {

    @MockBean
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ProductJSONSerializer productJSONSerializer;

    @Autowired
    @InjectMocks
    private ProductRepository productRepository;

    private Product product;
    private List<Product> products;

    @Before
    public void setup() {
        product = new Product();
        product.setName("TestProduct1");
        product.setDescription("Product1 to test the repository");
        product.setPrice(10L);
        product.setId(1L);

        products = new ArrayList<>();
        products.add(product);
        String productsJSON = productJSONSerializer.serializeList(products);
        String productJSON = productJSONSerializer.serializeObject(product);

        MockitoAnnotations.initMocks(this);

        when(rabbitTemplate.convertSendAndReceive("productExchange", "request.products", "request")).thenReturn(productsJSON);
        when(rabbitTemplate.convertSendAndReceive("productExchange", "store.product", productJSONSerializer.serializeObject(product))).thenReturn(productJSON);

    }

    @Test
    public void retrieveProductsTest() {
        List<Product> retrievedProducts = productRepository.retrieveProducts();
        assertThat(retrievedProducts).isNotNull();
        assertThat(retrievedProducts).hasSize(1);
    }

    @Test
    public void storeProductsTest() {
        Product storedProduct = null;
        storedProduct = productRepository.storeProduct(product);

        assertThat(storedProduct).isNotNull();
        assertThat(storedProduct.getId()).isEqualTo(1L);
    }

}