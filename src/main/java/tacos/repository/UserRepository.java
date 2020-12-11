package tacos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tacos.model.User;

/**
 * Created by phyriak on 26/11/2020
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
