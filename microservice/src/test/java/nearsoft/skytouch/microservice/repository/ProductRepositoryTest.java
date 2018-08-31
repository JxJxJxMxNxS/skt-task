package nearsoft.skytouch.microservice.repository;

import nearsoft.skytouch.common.model.Product;
import nearsoft.skytouch.microservice.repository.impl.ProductRepositoryImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductRepositoryTest {

    @PersistenceContext
    @Mock
    private EntityManager entityManager;

    @Autowired
    @InjectMocks
    private ProductRepositoryImpl productRepository;

    private Product product;
    private List<Product> products;

    @Before
    public void setup() {
        product = new Product();
        product.setName("TestProduct1");
        product.setDescription("Product1 to test the repository");
        product.setPrice(10L);
        product.setId(1L);
        List<Object[]> products = new ArrayList<>();
        Object productAsArray[] = new Object[4];
        productAsArray[0] = product.getName();
        productAsArray[1] = product.getDescription();
        productAsArray[2] = product.getPrice();
        productAsArray[3] = product.getId();

        products.add(productAsArray);


        StoredProcedureQuery mockedStoredProcedureQuery = mock(StoredProcedureQuery.class);
        MockitoAnnotations.initMocks(this);

        when(entityManager.createStoredProcedureQuery("getproducts")).thenReturn(mockedStoredProcedureQuery);
        when(mockedStoredProcedureQuery.registerStoredProcedureParameter(1, void.class, ParameterMode.REF_CURSOR)).thenReturn(mockedStoredProcedureQuery);
        when(mockedStoredProcedureQuery.getResultList()).thenReturn(products);

        when(entityManager.createStoredProcedureQuery("addproduct")).thenReturn(mockedStoredProcedureQuery);
        when(mockedStoredProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN)).thenReturn(mockedStoredProcedureQuery);
        when(mockedStoredProcedureQuery.registerStoredProcedureParameter(3, String.class, ParameterMode.IN)).thenReturn(mockedStoredProcedureQuery);
        when(mockedStoredProcedureQuery.registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)).thenReturn(mockedStoredProcedureQuery);

        when(mockedStoredProcedureQuery.setParameter(2, product.getName())).thenReturn(mockedStoredProcedureQuery);
        when(mockedStoredProcedureQuery.setParameter(3, product.getDescription())).thenReturn(mockedStoredProcedureQuery);
        when(mockedStoredProcedureQuery.setParameter(4, product.getPrice())).thenReturn(mockedStoredProcedureQuery);


    }

    @Test
    public void getProductsTest() {
        assertThat(productRepository.getProducts()).isNotNull();
    }

    @Test
    public void storeProductTest() {
        Product storedProduct = productRepository.storeProduct(product);
        assertThat(storedProduct).isNotNull();
        assertThat(storedProduct.getId()).isEqualTo(1L);
    }

}
