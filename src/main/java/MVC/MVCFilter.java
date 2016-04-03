package MVC;

import MVC.MVCControllers.customer.*;
import MVC.MVCControllers.taxi.*;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by D.Lazorkin on 31.03.2016.
 */
public class MVCFilter implements Filter {
    private Map<String, MVCController> urlToControllerMap;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        urlToControllerMap = new HashMap<>();
        urlToControllerMap.put("/customer", new CustomerMenuController());
        urlToControllerMap.put("/customer/customerAuthorization", new CustomerAuthorizationController());
        urlToControllerMap.put("/customer/customerRegistration", new CustomerRegistrationController());
        urlToControllerMap.put("/customer/customerEditProfile", new CustomerEditProfileController());
        urlToControllerMap.put("/customer/customerCreateNewOrder", new CustomerCreateNewOrderController());
        urlToControllerMap.put("/customer/customerHistoryOfOrders", new CustomerHistoryOfOrdersController());
        urlToControllerMap.put("/customer/customerEditDeleteOrders", new CustomerOrdersEditDeleteController());
        urlToControllerMap.put("/customer/customerDeleteOrder", new CustomerOrderDeleteController());
        urlToControllerMap.put("/customer/customerEditOrder", new CustomerOrderEditController());
        urlToControllerMap.put("/customer/customerWriteFeedbacksToOrders", new CustomerWriteFeedbacksController());
        urlToControllerMap.put("/customer/writeFeedbackToOrder", new CustomerWriteFeedbackController());
        urlToControllerMap.put("/customer/signOut", new CustomerSignOutController());


        urlToControllerMap.put("/taxi", new TaxiMenuController());
        urlToControllerMap.put("/taxi/registration", new TaxiRegistrationController());
        urlToControllerMap.put("/taxi/authorization", new TaxiAuthorizationController());
        urlToControllerMap.put("/taxi/logoff", new TaxiLogoffController());
        urlToControllerMap.put("/taxi/history", new TaxiHistoryController());
        urlToControllerMap.put("/taxi/openorders", new TaxiOpenOrdersController());
        urlToControllerMap.put("/taxi/takeorder", new TaxiTakeOrderController());
        urlToControllerMap.put("/taxi/completeorder", new TaxiCompleteOrderController());
        urlToControllerMap.put("/taxi/cancelorder", new TaxiCancelOrderController());
        urlToControllerMap.put("/taxi/editprofile", new TaxiEditProfileController());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
                                                                           throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String contextURL = request.getServletPath();
        MVCModel model = null;

        if (contextURL.equals("/index.html")) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else {

            MVCController controller = urlToControllerMap.get(contextURL);

            if (request.getMethod().equals("POST")){
                try {
                    model = controller.handlePostRequest(request);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else{
                try {
                    model = controller.handleGetRequest(request);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            request.setAttribute("model", model.getData());
            request.setAttribute("message", model.getMessage());

            ServletContext context = request.getServletContext();
            RequestDispatcher requestDispatcher = context.getRequestDispatcher(model.getJspName());
            requestDispatcher.forward(request, response);
        }

    }

    @Override
    public void destroy() {
    }

}
