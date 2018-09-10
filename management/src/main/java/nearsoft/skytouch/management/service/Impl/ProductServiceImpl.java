package nearsoft.skytouch.management.service.Impl;

import nearsoft.skytouch.common.model.Product;
import nearsoft.skytouch.management.repository.ProductRepository;
import nearsoft.skytouch.management.service.ProductService;
import org.springframework.amqp.AmqpException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> retrieveProducts() throws AmqpException, IOException {
        return productRepository.retrieveProducts();
    }

    @Override
    public Product storeProduct(Product product) throws AmqpException, IOException {
        return productRepository.storeProduct(product);
    }
}
