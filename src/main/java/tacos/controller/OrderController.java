package tacos.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import tacos.model.Order;
import tacos.model.UserPrincipal;
import tacos.repository.OrderRepository;

import javax.validation.Valid;


@Slf4j
@Controller
@AllArgsConstructor
@SessionAttributes("order")
@RequestMapping("/orders")
public class OrderController {

    private OrderRepository orderRepository;

    @GetMapping("/current")
    public String orderForm(Model model, @ModelAttribute("order") Order order) {
        //model.addAttribute("order", new Order());
        return "orderForm";
    }


    @PostMapping
    public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus) {
        int d = 4;
        if (errors.hasErrors()) {
            return "orderForm";
        }
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserPrincipal a = (UserPrincipal) principal;
        log.info("Zamówienie zostało złożone: " + order);
        order.setUser(a.getUser());
        orderRepository.save(order);
        sessionStatus.setComplete();
        return "home";
    }

}
