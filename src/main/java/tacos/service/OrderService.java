package tacos.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tacos.exception.OrderNotFound;
import tacos.model.Order;
import tacos.model.Taco;
import tacos.repository.OrderRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by phyriak on 17/12/2020
 */
@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;


    public Order createOrder(Order order) {
        order.setCreateAt(new Date());
        return orderRepository.save(order);
    }

    public Order updateOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order findOrderById(Long id) {
        if (orderRepository.findById(id).isPresent()) {
            return orderRepository.findById(id).get();
        } else throw new OrderNotFound("Order not found!");
    }

    public List<Order> findAlOrders() {
        return orderRepository.findAll();
    }

    public void deleteOrderById(Long id) {
        if (orderRepository.findById(id).isPresent()) {
            Order order = orderRepository.findById(id).get();
            orderRepository.delete(order);
        } else throw new OrderNotFound("Order not found!");
    }

/*    public boolean addTacoToOrder(Order order, Taco taco) {
        return order.getTacos().add(taco);
    }*/

}
