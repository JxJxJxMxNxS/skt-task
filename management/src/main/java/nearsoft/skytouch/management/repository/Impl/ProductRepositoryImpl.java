package nearsoft.skytouch.management.repository.Impl;

import nearsoft.skytouch.common.model.Product;
import nearsoft.skytouch.management.channel.ProductChannel;
import nearsoft.skytouch.management.producer.ProductProducer;
import nearsoft.skytouch.management.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Repository
class ProductRepositoryImpl implements ProductRepository {

    @Autowired
    private ProductChannel productChannel;



    public List<Product> retrieveProducts() {
        Product product;
        List<Product> products = new ArrayList<>();
        product = new Product();
        product.setName("Sabritas");
        product.setDescription("Saben bien");
        product.setPrice(20L);
        products.add(product);
        product = new Product();
        product.setName("Coca");
        product.setDescription("rico");
        product.setPrice(10L);
        products.add(product);
        product = new Product();
        product.setName("Gansito");
        product.setDescription("deliciosos cuando estan congelados");
        product.setPrice(12L);
        products.add(product);
        return products;
    }

    @Override
    public Product storeProduct(Product product) {
        /*HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Type", "application/x-www-form-urlencoded");
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("name", product.getName());
        map.add("description", product.getDescription());
        map.add("price", product.getPrice().toString());
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForLocation("http://localhost:8080/products", request);
        return null;*/
        productChannel.productOrders().send(MessageBuilder.withPayload(product).build());
        return null;
    }
}
