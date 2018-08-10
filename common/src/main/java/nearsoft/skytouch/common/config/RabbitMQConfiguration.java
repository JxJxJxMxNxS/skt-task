package nearsoft.skytouch.common.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    public static final String CREATE_PRODUCTS_CHANNEL_NAME = "createProductsChannel";
    public static final String REQUEST_PRODUCTS_CHANNEL_NAME = "requestProductsChannel";
    public static final String RECEIVE_PRODUCTS_CHANNEL_NAME = "receiveProductsChannel";

}
