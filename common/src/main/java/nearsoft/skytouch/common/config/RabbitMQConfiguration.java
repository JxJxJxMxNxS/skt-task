package nearsoft.skytouch.common.config;

import org.springframework.beans.factory.annotation.Value;

public class RabbitMQConfiguration {

    @Value("${channel-config.create-products-channel-name}")
    public static final String CREATE_PRODUCTS_CHANNEL_NAME = "createProductsChannel";
    @Value("${channel-config.request-products-channel-name}")
    public static final String REQUEST_PRODUCTS_CHANNEL_NAME = "requestProductsChannel";
    @Value("${channel-config.receive-products-channel-name}")
    public static final String RECEIVE_PRODUCTS_CHANNEL_NAME = "receiveProductsChannel";

}
