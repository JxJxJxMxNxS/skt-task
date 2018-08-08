package nearsoft.skytouch.microservice.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import nearsoft.skytouch.common.ProductJSONSerializer;
import nearsoft.skytouch.common.config.RabbitMQConfiguration;
import nearsoft.skytouch.common.model.Product;
import nearsoft.skytouch.microservice.channel.ProductChannel;
import nearsoft.skytouch.microservice.service.ProductService;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.support.MessageBuilder;

import java.util.List;

@EnableBinding(ProductChannel.class)
public class ProductConsumer {

    private ProductService productService;
    private ProductChannel productChannel;

    public ProductConsumer(ProductService productService, ProductChannel productChannel) {
        this.productService = productService;
        this.productChannel = productChannel;
    }

    @StreamListener(RabbitMQConfiguration.CREATE_PRODUCTS_CHANNEL_NAME)
    public void createProducts(Product product){
        productService.storeProduct(product);
    }

    @StreamListener(RabbitMQConfiguration.REQUEST_PRODUCTS_CHANNEL_NAME)
    public void manageRequestProducts(){
        ObjectMapper objectMapper = new ObjectMapper();
        ProductJSONSerializer serializer = new ProductJSONSerializer();
        List<Product> products = productService.getProducts();
        productChannel.sendProducts().send(MessageBuilder.withPayload(serializer.serialize(products)).build());


    }
}
