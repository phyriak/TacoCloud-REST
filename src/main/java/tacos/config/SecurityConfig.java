package tacos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import tacos.service.UserDetailsServiceImp;


import javax.sql.DataSource;

/**
 * Created by phyriak on 26/11/2020
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;


    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImp();
    };

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       /* auth.inMemoryAuthentication()
                .withUser("admin")
                .password(passwordEncoder().encode("admin"))
                .authorities("ROLE_ADMIN")
                .and()
                .withUser("user")
                .password(passwordEncoder().encode("user"))
                .authorities("ROLE_USER");*/

        auth
                .userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());



    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/design", "/orders","/orders/current").authenticated().and().httpBasic().and().csrf().disable();
    }
/*        http.csrf().disable()
                .authorizeRequests()


                .antMatchers("/design", "/orders","/orders/current").hasAnyRole("USER")
                //.antMatchers("/", "/**")
               // .permitAll()
                .anyRequest().authenticated();*/
              /*  .and()
                .formLogin();*/

              /*  .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()
                .defaultSuccessUrl("/design",true)
                .failureHandler(authenticationFailureHandler())
                .and()
                .logout()
                .logoutSuccessUrl("/logout")
                //.deleteCookies("JSESSIONID")
                .logoutSuccessHandler(logoutSuccessHandler()).and().rememberMe();*/



    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new CustomLogoutSuccessHandler();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
