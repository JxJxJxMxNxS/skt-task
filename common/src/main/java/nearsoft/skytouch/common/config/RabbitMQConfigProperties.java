package nearsoft.skytouch.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
@ConfigurationProperties(prefix = "rabbit-names-config")
public class RabbitMQConfigProperties {

    private String productsExchangeName;
    private String receiveProductsQueueName;
    private String requestProductsQueueName;
    private String storeProductsQueueName;
    private String storeReplyProductsQueueName;
    private String replyProductsRoutingKey;
    private String requestProductsRoutingKey;
    private String storeProductsRoutingKey;
    private String storeReplyProductsRoutingKey;

    public String getProductsExchangeName() {
        return productsExchangeName;
    }

    public void setProductsExchangeName(String productsExchangeName) {
        this.productsExchangeName = productsExchangeName;
    }

    public String getReceiveProductsQueueName() {
        return receiveProductsQueueName;
    }

    public void setReceiveProductsQueueName(String receivProductsQueueName) {
        this.receiveProductsQueueName = receivProductsQueueName;
    }

    public String getRequestProductsQueueName() {
        return requestProductsQueueName;
    }

    public void setRequestProductsQueueName(String requestProductsQueueName) {
        this.requestProductsQueueName = requestProductsQueueName;
    }

    public String getStoreProductsQueueName() {
        return storeProductsQueueName;
    }

    public void setStoreProductsQueueName(String storeProductsQueueName) {
        this.storeProductsQueueName = storeProductsQueueName;
    }

    public String getStoreReplyProductsQueueName() {
        return storeReplyProductsQueueName;
    }

    public void setStoreReplyProductsQueueName(String storeReplyProductsQueueName) {
        this.storeReplyProductsQueueName = storeReplyProductsQueueName;
    }

    public String getReplyProductsRoutingKey() {
        return replyProductsRoutingKey;
    }

    public void setReplyProductsRoutingKey(String replyProductsRoutingKey) {
        this.replyProductsRoutingKey = replyProductsRoutingKey;
    }

    public String getRequestProductsRoutingKey() {
        return requestProductsRoutingKey;
    }

    public void setRequestProductsRoutingKey(String requestProductsRoutingKey) {
        this.requestProductsRoutingKey = requestProductsRoutingKey;
    }

    public String getStoreProductsRoutingKey() {
        return storeProductsRoutingKey;
    }

    public void setStoreProductsRoutingKey(String storeProductsRoutingKey) {
        this.storeProductsRoutingKey = storeProductsRoutingKey;
    }

    public String getStoreReplyProductsRoutingKey() {
        return storeReplyProductsRoutingKey;
    }

    public void setStoreReplyProductsRoutingKey(String storeReplyProductsRoutingKey) {
        this.storeReplyProductsRoutingKey = storeReplyProductsRoutingKey;
    }
}
