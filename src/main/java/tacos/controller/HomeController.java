package tacos.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import tacos.model.Order;

@Slf4j
@Controller
@RequestMapping("/")
public class HomeController {


    @GetMapping()
    public String home(Model model) {
        return "home";
    }

    @GetMapping("logout")
    public String logout(Model model) {
        return "logout";
    }
}
