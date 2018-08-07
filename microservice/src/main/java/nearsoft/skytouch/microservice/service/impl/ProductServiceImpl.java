package nearsoft.skytouch.microservice.service.impl;

import nearsoft.skytouch.common.model.Product;
import nearsoft.skytouch.microservice.repository.ProductRepository;
import nearsoft.skytouch.microservice.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product storeProduct(Product product) {
       return productRepository.storeProduct(product);

    }

    @Override
    public List<Product> getProducts() {
       return productRepository.getProducts();
    }
}
