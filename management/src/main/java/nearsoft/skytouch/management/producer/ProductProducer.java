package nearsoft.skytouch.management.producer;

import nearsoft.skytouch.management.channel.ProductChannel;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(ProductChannel.class)
public class ProductProducer {
}
