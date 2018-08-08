package nearsoft.skytouch.microservice.channel;

import nearsoft.skytouch.common.config.RabbitMQConfiguration;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ProductChannel {
    @Input(RabbitMQConfiguration.CREATE_PRODUCTS_CHANNEL_NAME)
    MessageChannel createProduct();

    @Input(RabbitMQConfiguration.REQUEST_PRODUCTS_CHANNEL_NAME)
    MessageChannel manageRequestProducts();

    @Output(RabbitMQConfiguration.RECEIVE_PRODUCTS_CHANNEL_NAME)
    MessageChannel sendProducts();
}
