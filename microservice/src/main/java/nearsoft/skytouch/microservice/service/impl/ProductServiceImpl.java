package nearsoft.skytouch.microservice.service.impl;

import nearsoft.skytouch.common.model.Product;
import nearsoft.skytouch.microservice.repository.ProductRepository;
import nearsoft.skytouch.microservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public Product storeProduct(Product product) throws Exception {
        Long productId = productRepository.addProduct(product.getName(), product.getDescription(), product.getPrice());
        product.setId(productId);
        return product;
    }

    public List<Product> getProducts() throws Exception {
        return productRepository.getproducts();
    }
}
