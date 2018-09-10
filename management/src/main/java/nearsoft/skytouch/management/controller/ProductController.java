package nearsoft.skytouch.management.controller;

import nearsoft.skytouch.common.model.Product;
import nearsoft.skytouch.management.service.ProductService;
import org.springframework.amqp.AmqpException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@Controller
class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/view/product", method = RequestMethod.GET)
    private String newProductView(Map<String, Object> model) {
        Product emptyProduct = new Product();
        model.put("product", emptyProduct);
        return "products/newProduct";
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    private String submit(@Valid @ModelAttribute("product") Product product,
                          BindingResult bindingResult, Map<String, Object> model) {
        if (bindingResult.hasErrors()) {
            return "products/newProduct";
        }
        try {
            productService.storeProduct(product);
            model.put("product", new Product());
            return "redirect:/view/product";
        } catch (AmqpException e) {
            model.put("errors", "Failed to connect with the server try again");
            return "products/newProduct";
        } catch (IOException e) {
            model.put("errors", "Failed to parse the result");
            return "products/newProduct";
        }

    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    private String listProducts(Map<String, Object> model) {
        List<Product> products;
        try {
            products = productService.retrieveProducts();
            model.put("products", products);
            return "products/listProducts";
        } catch (AmqpException e) {
            model.put("errors", "Failed to connect with the server try again");
            return "products/listProducts";
        } catch (IOException e) {
            model.put("errors", "Failed to parse the result");
            return "products/newProduct";
        }
    }
}
