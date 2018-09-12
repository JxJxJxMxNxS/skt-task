package nearsoft.skytouch.management.controller;

import nearsoft.skytouch.common.model.Product;
import nearsoft.skytouch.management.ManagementAppException;
import nearsoft.skytouch.management.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
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
                          BindingResult bindingResult, Map<String, Object> model) throws ManagementAppException {
        if (bindingResult.hasErrors()) {
            return "products/newProduct";
        }
        productService.storeProduct(product);
        model.put("product", new Product());
        return "redirect:/view/product";
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    private String listProducts(Map<String, Object> model) throws ManagementAppException {
        List<Product> products;
        products = productService.retrieveProducts();
        model.put("products", products);
        return "products/listProducts";
    }

    @ExceptionHandler({ManagementAppException.class})
    public ModelAndView handleManagementAppException(ManagementAppException exception) {
        ModelAndView model = new ModelAndView();
        model.addObject("errors", "Failed to connect with the server try again");
        model.addObject("product", new Product());
        model.setViewName("products/newProduct");
        return model;
    }


}
