package tacos.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import tacos.dto.OrderDto;
import tacos.model.Order;
import tacos.model.Taco;
import tacos.model.User;
import tacos.model.UserPrincipal;
import tacos.repository.OrderRepository;
import tacos.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Date;


@Slf4j
@Controller
@AllArgsConstructor
@SessionAttributes("order")
@RequestMapping("/orders")
@CrossOrigin(origins = "*")
public class OrderController {
    private OrderRepository orderRepository;
    private UserRepository userRepository;

    @GetMapping("/current")
    public ResponseEntity<Order> orderForm(Model model, @ModelAttribute("order") Order order) {
        //Order order1 = new Order();
        // model.addAttribute("order", order);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }


    @PostMapping
    public ResponseEntity<Order> processOrder(@ModelAttribute("order") Order order, @RequestBody OrderDto orderDto, SessionStatus sessionStatus, Principal principal) {
        Order newOrder = orderFactory(order, orderDto);
        log.info("user principal: " + principal.getName().toString());
        User user = userRepository.findByUsername(principal.getName());
        newOrder.setUser(user);
        Order save = orderRepository.save(newOrder);
        sessionStatus.setComplete();
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    public Order orderFactory(Order order, OrderDto orderDto){
        return order.toBuilder()
                .name(orderDto.getName())
                .street(orderDto.getStreet())
                .city(orderDto.getCity())
                .state(orderDto.getState())
                .zip(orderDto.getZip())
                .ccNumber(orderDto.getCcNumber())
                .ccCVV(orderDto.getCcCVV())
                .ccExpiration(orderDto.getCcExpiration())
                .completed(true)
                .build();
    }

/*
    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Order> createOrder(@RequestBody Taco taco, Errors errors, @ModelAttribute("order") Order order) {
*//*    if (errors.hasErrors()) {
        log.info("errors");
    }
    Taco saved = tacoService.createTaco(taco);
    order.setCreateAt(new Date());
    order.addDesign(saved);
    log.info("Przetwarzanie projektu taco: " + taco);*//*
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }*/


    @PutMapping("/{orderId}")
    public Order putOrder(@RequestBody Order order) {
        return orderRepository.save(order);
    }

    @PatchMapping(path = "/{orderId}", consumes = "application/json")
    public Order patchOrder(@PathVariable("orderId") Long orderId, @RequestBody Order patch) {
        Order order = orderRepository.findById(orderId).get();
        if (patch.getName() != null) {
            order.setName(patch.getName());
        }
        if (patch.getStreet() != null) {
            order.setStreet(patch.getStreet());
        }
        if (patch.getCity() != null) {
            order.setCity(patch.getCity());
        }
        if (patch.getState() != null) {
            order.setState(patch.getState());
        }
        if (patch.getZip() != null) {
            order.setZip(patch.getState());
        }
        if (patch.getCcNumber() != null) {
            order.setCcNumber(patch.getCcNumber());
        }
        if (patch.getCcExpiration() != null) {
            order.setCcExpiration(patch.getCcExpiration());
        }
        if (patch.getCcCVV() != null) {
            order.setCcCVV(patch.getCcCVV());
        }
        return orderRepository.save(order);
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("orderId") Long orderId) {
        try {
            orderRepository.deleteById(orderId);
        } catch (EmptyResultDataAccessException e) {
        }
    }


}


