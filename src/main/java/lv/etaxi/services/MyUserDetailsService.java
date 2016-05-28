package lv.etaxi.services;

import lv.etaxi.dao.AdminDAO;
import lv.etaxi.dao.BaseDAO;
import lv.etaxi.dao.CustomerDAO;
import lv.etaxi.dao.TaxiDAO;
import lv.etaxi.entity.Admin;
import lv.etaxi.entity.Customer;
import lv.etaxi.entity.Taxi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitrij Lazorkin on 26.05.2016.
 */
@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private CustomerDAO customerDAO;

    @Autowired
    private TaxiDAO taxiDAO;

    @Autowired
    private AdminDAO adminDAO;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String userName){

        Customer customer = getEntityByLogin(customerDAO, userName);
        if (customer != null) {
            List<GrantedAuthority> authorities = buildUserAuthority("ROLE_CUSTOMER");
            return buildUserForAuthentication(customer.getPhone(), customer.getPassword(), authorities);
        };

        Taxi taxi = getEntityByLogin(taxiDAO, userName);
        if (taxi != null) {
            List<GrantedAuthority> authorities = buildUserAuthority("ROLE_TAXI");
            return buildUserForAuthentication(taxi.getLogin(), taxi.getPassword(), authorities);
        };

        Admin admin = getEntityByLogin(adminDAO, userName);
        if (admin != null) {
            List<GrantedAuthority> authorities = buildUserAuthority("ROLE_ADMIN");
            return buildUserForAuthentication(admin.getLogin(), admin.getPassword(), authorities);
        };

        return null;
    }

    // Create  org.springframework.security.core.userdetails.User
    private UserPrincipal buildUserForAuthentication(String login, String password,
                                                     List<GrantedAuthority> authorities) {

        // Creating user that will be stored by Spring security
        UserPrincipal userPrincipal = new UserPrincipal(
                login,
                password,
                true, true, true, true,
                authorities);

        return  userPrincipal;
    }

    private List<GrantedAuthority> buildUserAuthority(String role) {

        List<GrantedAuthority> result = new ArrayList<GrantedAuthority>();
        result.add(new SimpleGrantedAuthority(role));

        return result;
    }

    private <T> T getEntityByLogin(BaseDAO<T> dao, String username) {
        T value = null;
        try {
            value = dao.getByLogin(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    };

}
