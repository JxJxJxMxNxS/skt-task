package nearsoft.skytouch.management.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ProductChannel {
    @Output("createProductsChannel")
    MessageChannel createProduct();
    @Output("requestProductsChannel")
    MessageChannel requestProducts();
    @Input("receiveProductsChannel")
    MessageChannel receiveProducts();
}
