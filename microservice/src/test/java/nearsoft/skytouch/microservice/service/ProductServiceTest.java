package nearsoft.skytouch.microservice.service;

import nearsoft.skytouch.common.model.Product;
import nearsoft.skytouch.microservice.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
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
public class ProductServiceTest {

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    @InjectMocks
    private ProductService productService;

    private Product product;

    @Before
    public void setup() {
        product = new Product();
        product.setName("TestProduct");
        product.setDescription("Product to test the store product method");
        product.setPrice(10L);
        product.setId(1L);

        MockitoAnnotations.initMocks(this);
        when(productRepository.getProducts()).thenReturn(new ArrayList<Product>());
        when(productRepository.storeProduct(product)).thenReturn(product);
    }

    @Test
    public void getProductsTest() {
        List<Product> products = productService.getProducts();
        assertThat(products).isNotNull();
    }

    @Test
    public void storeProductTest() {
        Product storedProduct = null;
        storedProduct = productService.storeProduct(product);

        assertThat(storedProduct).isNotNull();
        assertThat(storedProduct.getId()).isEqualTo(1L);
    }

}
