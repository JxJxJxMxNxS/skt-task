package nearsoft.skytouch.task.controller;

import nearsoft.skytouch.task.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
public class ProductController {

    @RequestMapping(value= "/view/product", method = RequestMethod.GET)
    public String addProducts(Map<String, Object> model) {
        model.put("product",new Product());
        return "/products/newProduct";
    }

    @RequestMapping(value="/products", method = RequestMethod.POST)
    public String submit(@ModelAttribute("product")Product product,
                         BindingResult bindingResult,Map<String, Object> model) {
        if (bindingResult.hasErrors()) {
            model.put("errors",bindingResult.getAllErrors());
            return "/errors/error";
        }
        return "/products/newProduct";
    }

    @RequestMapping(value="/products", method=RequestMethod.GET)
    public String listProducts(Map<String, Object> model){
        Product product;
        List<Product> products = new ArrayList<>();
        product=new Product();
        product.setName("Sabritas");
        product.setDescription("Saben bien");
        product.setPrice(20L);
        products.add(product);
        product=new Product();
        product.setName("coca");
        product.setDescription("Sabe bien");
        product.setPrice(10L);
        products.add(product);
        product=new Product();
        product.setName("Gansito");
        product.setDescription("Saben bien");
        product.setPrice(12L);
        products.add(product);
        model.put("products",products);
        return "/products/listProducts";
    }
}
