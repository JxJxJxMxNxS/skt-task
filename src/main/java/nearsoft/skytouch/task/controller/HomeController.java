package nearsoft.skytouch.task.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String welcome(Map<String, Object> model) {
        return "/home";
    }


}
