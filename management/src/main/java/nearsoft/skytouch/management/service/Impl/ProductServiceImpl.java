package nearsoft.skytouch.management.service.Impl;

import nearsoft.skytouch.common.model.Product;
import nearsoft.skytouch.management.ManagementAppException;
import nearsoft.skytouch.management.repository.ProductRepository;
import nearsoft.skytouch.management.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> retrieveProducts() throws ManagementAppException {
        return productRepository.retrieveProducts();
    }

    @Override
    public Product storeProduct(Product product) throws ManagementAppException {
        return productRepository.storeProduct(product);
    }
}
