package tacos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tacos.model.Taco;

/**
 * Created by phyriak on 12/11/2020
 */
public interface TacoRepository extends JpaRepository <Taco, Long> {

}
