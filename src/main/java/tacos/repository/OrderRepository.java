package tacos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tacos.model.Order;

/**
 * Created by phyriak on 12/11/2020
 */
public interface OrderRepository extends JpaRepository<Order, Long> {

}
