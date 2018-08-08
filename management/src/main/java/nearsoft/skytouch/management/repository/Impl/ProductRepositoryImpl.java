package nearsoft.skytouch.management.repository.Impl;

import nearsoft.skytouch.common.model.Product;
import nearsoft.skytouch.management.channel.ProductChannel;
import nearsoft.skytouch.management.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
class ProductRepositoryImpl implements ProductRepository {

    @Autowired
    private ProductChannel productChannel;



    public List<Product> retrieveProducts() {
        productChannel.requestProducts().send(MessageBuilder.withPayload("").build());
        return null;
    }

    @Override
    public Product storeProduct(Product product) {
        productChannel.createProduct().send(MessageBuilder.withPayload(product).build());
        return null;
    }
}
