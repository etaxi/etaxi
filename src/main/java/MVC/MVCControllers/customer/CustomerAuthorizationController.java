package MVC.MVCControllers.customer;

import MVC.MVCController;
import MVC.MVCModel;
import business.CustomerManager;
import entity.Customer;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * Created by D.Lazorkin on 31.03.2016.
 */
public class CustomerAuthorizationController implements MVCController {

    @Override
    public MVCModel handleRequest(HttpServletRequest request) {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        Customer customer = null;
        try {
            customer = new CustomerManager().findCustomerByLogin(login);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (customer == null | !customer.getPassword().equals(password)) {
            return new MVCModel("/customer/CustomerAuthorization.jsp", "Wrong username or password!");
        }

        // сохраняем логин (телефон) пользователя в сессию, для дальнейшем идентификации клиента в системе
        request.getSession().setAttribute("customerId", customer.getCustomerId());

        return new MVCModel("/customer/CustomerMenu.jsp", "Authorization successful: " + customer.getName());

    }

}
