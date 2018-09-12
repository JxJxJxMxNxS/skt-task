package nearsoft.skytouch.management.service;

import nearsoft.skytouch.common.model.Product;
import nearsoft.skytouch.management.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
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
        try {
            when(productRepository.retrieveProducts()).thenReturn(new ArrayList<>());
            when(productRepository.storeProduct(product)).thenReturn(product);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void retrieveProductsTest() {
        List<Product> products = null;
        try {
            products = productService.retrieveProducts();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(products).isNotNull();
    }

    @Test
    public void storeProductsTest() {
        Product storedProduct = null;
        try {
            storedProduct = productService.storeProduct(product);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertThat(storedProduct).isNotNull();
        assertThat(storedProduct.getId()).isEqualTo(1L);
    }

}