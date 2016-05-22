package lv.etaxi.config;

import lv.etaxi.business.CustomerManager;
import lv.etaxi.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.List;

/**Spring security configuration
 * Created by D.Lazorkin on 16.04.2016.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomerManager customerManagerImpl;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/","/customer/customerRegistration").permitAll()
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/customer/**").access("hasRole('ROLE_CUSTOMER')")
                .antMatchers("/taxi/**").access("hasRole('ROLE_TAXI')")
                .and().formLogin().loginPage("/loginSpringSecurity").permitAll()
                .and().logout().permitAll();

        http.csrf().disable();
    }

    private void customersInMemoryAuthentication(AuthenticationManagerBuilder authentication) {

        try {
            List<Customer> listOfCustomers = customerManagerImpl.getAllCustomers();
            for (Customer customer : listOfCustomers) {
                authentication.inMemoryAuthentication().
                        withUser(customer.getPhone()).
                        password(customer.getPassword()).
                        roles("CUSTOMER");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authentication) throws Exception {

        customersInMemoryAuthentication(authentication);

        //Examples (for taxi and admin):
        //authentication.inMemoryAuthentication().withUser("admin").password("123456").roles("ADMIN");
        //authentication.inMemoryAuthentication().withUser("taxi").password("123456").roles("TAXI");
    }

}

