package nearsoft.skytouch.microservice.channel;

import org.springframework.cloud.stream.annotation.Input;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ProductChannel {
    @Input("createProductsChannel")
    MessageChannel createProduct();
    @Input("requestProductsChannel")
    MessageChannel manageRequestProducts();
    @Output("receiveProductsChannel")
    MessageChannel sendProducts();
}
