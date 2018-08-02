package nearsoft.skytouch.management.controller;

import nearsoft.skytouch.common.model.Product;
import nearsoft.skytouch.management.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;


@Controller
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/view/product", method = RequestMethod.GET)
    private String newProductView(Map<String, Object> model) {
        model.put("product", new Product());
        return "products/newProduct";
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    private String submit(@ModelAttribute("product") Product product,
                          BindingResult bindingResult, Map<String, Object> model) {
        if (bindingResult.hasErrors()) {
            model.put("errors", bindingResult.getAllErrors());
            return "errors/error";
        }
        model.put("product",new Product());
        return "products/newProduct";
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    private String listProducts(Map<String, Object> model) {
        List<Product> products;
        products = productService.retrieveProducts();
        model.put("products", products);
        return "products/listProducts";
    }
}
