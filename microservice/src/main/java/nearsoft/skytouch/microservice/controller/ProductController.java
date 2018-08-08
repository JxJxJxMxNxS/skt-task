package nearsoft.skytouch.microservice.controller;

import nearsoft.skytouch.common.model.Product;
import nearsoft.skytouch.microservice.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "products", method = RequestMethod.GET)
    private ResponseEntity<List<Product>> getProducts() {
        List<Product> products = new ArrayList<>();

        return new ResponseEntity<>(this.productService.getProducts(), HttpStatus.OK);
    }

    @RequestMapping(value = "products", method = RequestMethod.POST)
    private ResponseEntity<String> saveProduct(@RequestParam("name") String name, @RequestParam("description") String description, @RequestParam("price") Long price) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);
        this.productService.storeProduct(product);
        return new ResponseEntity<>("Bien", HttpStatus.FORBIDDEN);
    }
}
