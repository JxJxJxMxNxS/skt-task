package nearsoft.skytouch.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
class HomeController {
    @RequestMapping("/")
    private String welcome(Map<String, Object> model) {
        return "/home";
    }


}
