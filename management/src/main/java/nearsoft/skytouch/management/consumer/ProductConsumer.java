package nearsoft.skytouch.management.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import nearsoft.skytouch.common.model.Product;
import nearsoft.skytouch.management.channel.ProductChannel;
import nearsoft.skytouch.management.service.ProductService;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import java.io.IOException;
import java.util.List;

@EnableBinding(ProductChannel.class)
public class ProductConsumer {
    private ProductService productService;

    public ProductConsumer(ProductService productService) {
        this.productService = productService;
    }

    @StreamListener("receiveProductsChannel")
    public void receiveProducts(List<Product> product){
        /*ObjectMapper objectMapper = new ObjectMapper();
        List<Product> products = null;
        try {
           objectMapper.readValue(productsJson, List.class);
        } catch (IOException e) {
            e.printStackTrace();
        }/*/
    }
}
