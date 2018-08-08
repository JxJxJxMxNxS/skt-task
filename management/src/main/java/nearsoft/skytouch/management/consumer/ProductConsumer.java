package nearsoft.skytouch.management.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import nearsoft.skytouch.common.ProductJSONSerializer;
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
    public void receiveProducts(String productsJson){
        ProductJSONSerializer serializer = new ProductJSONSerializer();
        List<Product> products = serializer.deserialize(productsJson);
        System.out.println(products);


    }
}
