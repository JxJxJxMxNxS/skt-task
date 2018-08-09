package nearsoft.skytouch.management.repository.Impl;

import nearsoft.skytouch.common.ProductJSONSerializer;
import nearsoft.skytouch.common.config.RabbitMQConfiguration;
import nearsoft.skytouch.common.model.Product;
import nearsoft.skytouch.management.channel.ProductChannel;
import nearsoft.skytouch.management.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableBinding(ProductChannel.class)
@Repository
class ProductRepositoryImpl implements ProductRepository {

    private final static Logger LOGGER = LoggerFactory.getLogger(ProductRepositoryImpl.class);
    private ProductJSONSerializer productJSONSerializer;
    private ProductChannel productChannel;
    private RabbitTemplate rabbitTemplate;


    public ProductRepositoryImpl(ProductJSONSerializer productJSONSerializer, ProductChannel productChannel, RabbitTemplate rabbitTemplate) {
        this.productJSONSerializer = productJSONSerializer;
        this.productChannel = productChannel;
        this.rabbitTemplate = rabbitTemplate;
    }

    public List<Product> retrieveProducts() {
        productChannel.requestProducts().send(MessageBuilder.withPayload("").build());
        return null;
    }

    @Override
    public Product storeProduct(Product product) {
        productChannel.createProduct().send(MessageBuilder.withPayload(productJSONSerializer.serializeObject(product)).build());
        return null;
    }

    @StreamListener(RabbitMQConfiguration.RECEIVE_PRODUCTS_CHANNEL_NAME)
    public void receiveProducts(String productsJson) {
        List<Product> products = productJSONSerializer.deserializeList(productsJson);
        LOGGER.info("Products {}", products);
    }
}
