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

        List<Product> products = new ArrayList<Product>();
        products.add(product);

        MockitoAnnotations.initMocks(this);
        try {
            when(productRepository.getproducts()).thenReturn(products);
            when(productRepository.addProduct(product.getName(), product.getDescription(), product.getPrice())).thenReturn(1L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getProductsTest() {
        List<Product> products = null;
        try {
            products = productService.getProducts();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(products).isNotNull();
        assertThat(products).hasSize(1);
        assertThat(products.get(0).getName()).matches(product.getName());
    }

    @Test
    public void storeProductTest() {
        Product storedProduct = null;
        try {
            storedProduct = productService.storeProduct(product);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertThat(storedProduct).isNotNull();
        assertThat(storedProduct.getId()).isEqualTo(1L);
    }

}