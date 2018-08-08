package nearsoft.skytouch.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import nearsoft.skytouch.common.model.Product;

import java.io.IOException;
import java.util.List;

public class ProductJSONSerializer {
    public List<Product> deserialize(String json){
        ObjectMapper mapper = new ObjectMapper();
        List<Product> productList = null;
        try {
            productList = mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, Product.class));
            return productList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return productList;
    }

    public String serialize(List<Product> products) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(products);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
