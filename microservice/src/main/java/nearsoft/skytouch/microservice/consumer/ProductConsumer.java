package nearsoft.skytouch.microservice.consumer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nearsoft.skytouch.common.model.Product;
import nearsoft.skytouch.microservice.channel.ProductChannel;
import nearsoft.skytouch.microservice.service.ProductService;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.cloud.stream.messaging.Sink;

import java.util.List;

@EnableBinding(ProductChannel.class)
public class ProductConsumer {

    private ProductService productService;
    private ProductChannel productChannel;

    public ProductConsumer(ProductService productService, ProductChannel productChannel) {
        this.productService = productService;
        this.productChannel = productChannel;
    }

    @StreamListener("createProductsChannel")
    public void createProducts(Product product){
        productService.storeProduct(product);
    }

    @StreamListener("requestProductsChannel")
    public void manageRequestProducts(){
        ObjectMapper objectMapper = new ObjectMapper();

        List<Product> products = productService.getProducts();
       // try {
         //   String productsJson = objectMapper.writeValueAsString(products);
            productChannel.sendProducts().send(MessageBuilder.withPayload(products).build());
       // } catch (JsonProcessingException e) {
        //    e.printStackTrace();
      //  }

    }
}
