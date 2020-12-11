package tacos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tacos.model.Type;

/**
 * Created by phyriak on 01/12/2020
 */
public interface TypeRepository extends JpaRepository<Type, Long> {
}
