package nearsoft.skytouch.management.repository.Impl;

import nearsoft.skytouch.common.model.Product;
import nearsoft.skytouch.management.channel.ProductChannel;
import nearsoft.skytouch.management.producer.ProductProducer;
import nearsoft.skytouch.management.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
class ProductRepositoryImpl implements ProductRepository {

    @Autowired
    private ProductChannel productChannel;



    public List<Product> retrieveProducts() {
       /* RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = "http://localhost:8080/products";
        ResponseEntity<Product[]> response
                = restTemplate.getForEntity(fooResourceUrl, Product[].class);
        Product[] p = response.getBody();*/
        productChannel.requestProducts().send(MessageBuilder.withPayload("").build());
        return null;
        //return Arrays.asList(p);
    }

    @Override
    public Product storeProduct(Product product) {
        productChannel.createProduct().send(MessageBuilder.withPayload(product).build());
        return null;
    }
}
