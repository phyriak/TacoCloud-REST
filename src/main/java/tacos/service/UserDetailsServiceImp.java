package tacos.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import tacos.model.User;
import tacos.model.UserPrincipal;
import tacos.repository.UserRepository;

import javax.annotation.PostConstruct;

/**
 * Created by phyriak on 26/11/2020
 */
@Service
public class UserDetailsServiceImp implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private WebApplicationContext applicationContext;

    public UserDetailsServiceImp() {
        super();
    }

    @PostConstruct
    public void completeSetup() {
        userRepo = applicationContext.getBean(UserRepository.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userRepo.findByUsername(username);
        if (user != null) {

            return new UserPrincipal(user);
        }
        throw new UsernameNotFoundException(
                "Użytkownik '" + username + "' nie został znaleziony.");
    }


}
