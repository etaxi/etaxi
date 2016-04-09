package lv.etaxi.MVC.MVCControllers.customer;

import lv.etaxi.MVC.MVCController;
import lv.etaxi.MVC.MVCModel;
import lv.etaxi.business.CustomerManagerImpl;
import lv.etaxi.entity.Customer;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by D.Lazorkin on 31.03.2016.
 */

@Component
public class CustomerAuthorizationController implements MVCController {

    @Override
    public MVCModel handleGetRequest(HttpServletRequest request) {

        return new MVCModel("/customer/CustomerAuthorization.jsp", null, "");
    }

    @Override
    public MVCModel handlePostRequest(HttpServletRequest request) {

        Customer currentCustomer = new CustomerManagerImpl().CheckAuthorization(
                request.getParameter("login"),
                request.getParameter("password"));

        if (currentCustomer != null) {
            // сохраняем пользователя в сессию, для дальнейшей идентификации клиента в системе
            request.getSession().setAttribute("customer", currentCustomer);
            return new MVCModel("/customer/CustomerMenu.jsp", currentCustomer, "Authorization successful: " + currentCustomer.getName());
        }
        else {
            return  new MVCModel("/customer/CustomerMenu.jsp", null, "Wrong login or password!");
        }
    }

}