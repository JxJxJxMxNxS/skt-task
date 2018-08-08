package nearsoft.skytouch.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nearsoft.skytouch.common.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class ProductJSONSerializer {

    private final static Logger LOGGER = LoggerFactory.getLogger(ProductJSONSerializer.class);

    public Product deserializeObject(String productJSON) {
        ObjectMapper mapper = new ObjectMapper();
        Product product = null;
        try {
            product = mapper.readValue(productJSON, Product.class);
            return product;
        } catch (IOException e) {
            LOGGER.error(e.getStackTrace().toString());
        }
        return product;
    }

    public String serializeObject(Product product) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(product);
        } catch (JsonProcessingException e) {
            LOGGER.error(e.getStackTrace().toString());
        }
        return null;
    }

    public List<Product> deserializeList(String productsJSON) {
        ObjectMapper mapper = new ObjectMapper();
        List<Product> productList = null;
        try {
            productList = mapper.readValue(productsJSON, mapper.getTypeFactory().constructCollectionType(List.class, Product.class));
            return productList;
        } catch (IOException e) {
            LOGGER.error(e.getStackTrace().toString());
        }
        return productList;
    }

    public String serializeList(List<Product> products) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(products);
        } catch (JsonProcessingException e) {
            LOGGER.error(e.getStackTrace().toString());
        }
        return null;
    }
}
