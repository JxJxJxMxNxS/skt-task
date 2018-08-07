package nearsoft.skytouch.management.channel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ProductChannel {
    @Output("productOrdersChannel")
    MessageChannel productOrders();
}
