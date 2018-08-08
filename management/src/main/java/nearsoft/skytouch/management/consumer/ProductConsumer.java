package nearsoft.skytouch.management.consumer;

import nearsoft.skytouch.common.ProductJSONSerializer;
import nearsoft.skytouch.common.config.RabbitMQConfiguration;
import nearsoft.skytouch.common.model.Product;
import nearsoft.skytouch.management.channel.ProductChannel;
import nearsoft.skytouch.management.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import java.util.List;

@EnableBinding(ProductChannel.class)
public class ProductConsumer {
    private ProductService productService;
    private final static Logger LOGGER = LoggerFactory.getLogger(ProductConsumer.class);

    public ProductConsumer(ProductService productService) {
        this.productService = productService;
    }

    @StreamListener(RabbitMQConfiguration.RECEIVE_PRODUCTS_CHANNEL_NAME)
    public void receiveProducts(String productsJson){
        ProductJSONSerializer serializer = new ProductJSONSerializer();
        List<Product> products = serializer.deserialize(productsJson);
        LOGGER.info("Products {}", products);
    }
}
