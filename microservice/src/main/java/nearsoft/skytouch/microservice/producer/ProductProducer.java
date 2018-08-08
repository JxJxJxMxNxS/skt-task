package nearsoft.skytouch.microservice.producer;

import nearsoft.skytouch.microservice.channel.ProductChannel;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(ProductChannel.class)
public class ProductProducer {
}
