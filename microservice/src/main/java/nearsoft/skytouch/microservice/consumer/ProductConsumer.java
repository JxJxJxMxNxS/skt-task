package nearsoft.skytouch.microservice.consumer;


import nearsoft.skytouch.common.model.Product;
import nearsoft.skytouch.microservice.service.ProductService;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(Sink.class)
public class ProductConsumer {

    private ProductService productService;

    public ProductConsumer(ProductService productService) {
        this.productService = productService;
    }

    @StreamListener(target = Sink.INPUT)
    public void processCheapMeals(Product product){
        productService.storeProduct(product);
    }
}
