package nearsoft.skytouch.microservice.consumer.impl;

import nearsoft.skytouch.common.ProductJSONSerializer;
import nearsoft.skytouch.common.config.RabbitMQConfiguration;
import nearsoft.skytouch.microservice.channel.ProductChannel;
import nearsoft.skytouch.microservice.consumer.MicroServiceConsumer;
import nearsoft.skytouch.microservice.service.ProductService;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.support.MessageBuilder;

@EnableBinding(ProductChannel.class)
public class MicroserviceConsumerImpl implements MicroServiceConsumer {

    private ProductJSONSerializer productJSONSerializer;
    private ProductChannel productChannel;
    private ProductService productService;

    public MicroserviceConsumerImpl(ProductJSONSerializer productJSONSerializer, ProductChannel productChannel, ProductService productService) {
        this.productJSONSerializer = productJSONSerializer;
        this.productChannel = productChannel;
        this.productService = productService;
    }

    @StreamListener(RabbitMQConfiguration.CREATE_PRODUCTS_CHANNEL_NAME)
    @Override
    public void storeProduct(String productJSON) {
        this.productService.storeProduct(productJSONSerializer.deserializeObject(productJSON));
    }

    @StreamListener(RabbitMQConfiguration.REQUEST_PRODUCTS_CHANNEL_NAME)
    @Override
    public void getProducts() {
        productChannel.sendProducts().send(MessageBuilder.withPayload(productJSONSerializer.serializeList(productService.getProducts())).build());
    }
}
