package nearsoft.skytouch.task.service.Impl;

import nearsoft.skytouch.task.model.Product;
import nearsoft.skytouch.task.repository.ProductRepository;
import nearsoft.skytouch.task.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> retrieveProducts() {
        return productRepository.retrieveProducts();
    }
}
