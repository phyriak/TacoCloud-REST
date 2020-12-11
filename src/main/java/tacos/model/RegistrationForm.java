package tacos.model;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by phyriak on 30/11/2020
 */
@Data
public class  RegistrationForm {
        private String username;
        private String password;
        private String street;
        private String city;
        private String state;
        private String zip;
        private String phone;
        public User toUser(PasswordEncoder passwordEncoder) {
            return new User(
                    username, passwordEncoder.encode(password),
                    street, city, state, zip, phone, "ROLE_USER");
        }
    }
