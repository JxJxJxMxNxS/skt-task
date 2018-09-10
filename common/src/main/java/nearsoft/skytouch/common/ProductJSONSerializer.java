package nearsoft.skytouch.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nearsoft.skytouch.common.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class ProductJSONSerializer {

    private final static Logger LOGGER = LoggerFactory.getLogger(ProductJSONSerializer.class);

    public Product deserializeObject(String productJSON) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Product product = null;
        product = mapper.readValue(productJSON, Product.class);
        return product;
    }

    public String serializeObject(Product product) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(product);

    }

    public List<Product> deserializeList(String productsJSON) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<Product> productList = null;
        productList = mapper.readValue(productsJSON, mapper.getTypeFactory().constructCollectionType(List.class, Product.class));
        return productList;
    }

    public String serializeList(List<Product> products) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(products);
    }
}
