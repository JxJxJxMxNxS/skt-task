package nearsoft.skytouch.management.channel;

import nearsoft.skytouch.common.config.RabbitMQConfiguration;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ProductChannel {

    @Output(RabbitMQConfiguration.CREATE_PRODUCTS_CHANNEL_NAME)
    MessageChannel createProduct();

    @Output(RabbitMQConfiguration.REQUEST_PRODUCTS_CHANNEL_NAME)
    MessageChannel requestProducts();

    @Input(RabbitMQConfiguration.RECEIVE_PRODUCTS_CHANNEL_NAME)
    MessageChannel receiveProducts();
}
