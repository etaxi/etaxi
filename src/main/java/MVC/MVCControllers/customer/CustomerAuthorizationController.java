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
    public MVCModel handleGetRequest(HttpServletRequest request) {
        return new MVCModel("/customer/CustomerAuthorization.jsp", "");
    }

    @Override
    public MVCModel handlePostRequest(HttpServletRequest request) {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        Customer currentCustomer = null;
        try {
            currentCustomer = new CustomerManager().findCustomerByLogin(login);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (currentCustomer == null) {
            return new MVCModel("/customer/CustomerAuthorization.jsp", "Wrong username or password!");
        }
        else if (!currentCustomer.getPassword().equals(password)) {
            return new MVCModel("/customer/CustomerAuthorization.jsp", "Wrong username or password!");
        }

        // сохраняем пользователя в сессию, для дальнейшей идентификации клиента в системе
        request.getSession().setAttribute("customer", currentCustomer);

        return new MVCModel("/customer/CustomerMenu.jsp", "Authorization successful: " + currentCustomer.getName());

    }

}
