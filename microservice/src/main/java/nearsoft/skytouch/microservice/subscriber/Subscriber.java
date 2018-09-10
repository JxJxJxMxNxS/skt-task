package nearsoft.skytouch.microservice.subscriber;

import nearsoft.skytouch.common.ProductJSONSerializer;
import nearsoft.skytouch.common.model.Product;
import nearsoft.skytouch.microservice.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.List;

public class Subscriber {

    private final static Logger LOGGER = LoggerFactory.getLogger(Subscriber.class);
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductJSONSerializer productJSONSerializer;

    @RabbitHandler
    @RabbitListener(queues = "requestProductsQueue")
    public String subscribeToRequestQueue(@Payload String requestMessage, Message message) {
        LOGGER.debug("Request received");
        List<Product> products = null;
        try {
            products = this.productService.getProducts();
            return productJSONSerializer.serializeList(products);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RabbitHandler
    @RabbitListener(queues = "storeProductsQueue")
    public String subscribeTostoreQueue(@Payload String productMessage, Message message) {

        LOGGER.debug("Product received to storage {}", productMessage);
        try {
            return productJSONSerializer.serializeObject(productService.storeProduct(productJSONSerializer.deserializeObject(productMessage)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}