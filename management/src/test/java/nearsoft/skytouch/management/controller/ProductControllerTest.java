package nearsoft.skytouch.management.controller;

import nearsoft.skytouch.common.model.Product;
import nearsoft.skytouch.management.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    @InjectMocks
    private MockMvc mvc;
    @LocalServerPort
    private int port;
    @MockBean
    private ProductService productService;
    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        Product product = new Product();

        MockitoAnnotations.initMocks(this);

        when(productService.storeProduct(product)).thenReturn(product);
    }


    @Test
    public void newProductViewTest() {
        assertThat(restTemplate.getForObject("http://localhost:" + port + "/view/product", String.class))
                .contains("Enter the product details");
    }

    @Test
    public void listProductsTest() throws Exception {

        assertThat(restTemplate.getForObject("http://localhost:" + port + "/products", String.class))
                .contains("Product list");
    }

    @Test
    public void submitTest() throws Exception {

        Product product = new Product();

        mvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(product.toString())).andExpect(status().is3xxRedirection());

    }
}
