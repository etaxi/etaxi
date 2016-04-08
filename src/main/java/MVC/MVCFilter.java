package MVC;

import MVC.MVCControllers.customer.HelloWorldController;
import config.SpringAppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
    private ApplicationContext springContext;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        springContext = new AnnotationConfigApplicationContext(SpringAppConfig.class);

        urlToControllerMap = new HashMap<>();

        urlToControllerMap.put("/hello", getBean(HelloWorldController.class));

        //urlToControllerMap.put("/customer", getBean(CustomerMenuController.class));
        //urlToControllerMap.put("/customer/customerAuthorization", getBean(CustomerAuthorizationController.class));
        //urlToControllerMap.put("/customer/customerRegistration", getBean(CustomerRegistrationController.class));

//        urlToControllerMap.put("/customer/customerEditProfile", getBean(CustomerEditProfileController.class));
//        urlToControllerMap.put("/customer/customerCreateNewOrder", getBean(CustomerCreateNewOrderController.class));
//        urlToControllerMap.put("/customer/customerHistoryOfOrders", getBean(CustomerHistoryOfOrdersController.class));
//        urlToControllerMap.put("/customer/customerEditDeleteOrders", getBean(CustomerOrdersEditDeleteController.class));
//        urlToControllerMap.put("/customer/customerDeleteOrder", getBean(CustomerOrderDeleteController.class));
//        urlToControllerMap.put("/customer/customerEditOrder", getBean(CustomerOrderEditController.class));
//        urlToControllerMap.put("/customer/customerWriteFeedbacksToOrders", getBean(CustomerWriteFeedbacksController.class));
//        urlToControllerMap.put("/customer/writeFeedbackToOrder", getBean(CustomerWriteFeedbackController.class));
//        urlToControllerMap.put("/customer/signOut", getBean(CustomerSignOutController.class));


//        urlToControllerMap.put("/taxi", getBean(TaxiMenuController.class));
//        urlToControllerMap.put("/taxi/registration", getBean(TaxiRegistrationController.class));
//        urlToControllerMap.put("/taxi/authorization", getBean(TaxiAuthorizationController.class));
//        urlToControllerMap.put("/taxi/logoff", getBean(TaxiLogoffController.class));
//        urlToControllerMap.put("/taxi/history", getBean(TaxiHistoryController.class));
//        urlToControllerMap.put("/taxi/openorders", getBean(TaxiOpenOrdersController.class));
//        urlToControllerMap.put("/taxi/takeorder", getBean(TaxiTakeOrderController.class));
//        urlToControllerMap.put("/taxi/completeorder", getBean(TaxiCompleteOrderController.class));
//        urlToControllerMap.put("/taxi/cancelorder", getBean(TaxiCancelOrderController.class));
//        urlToControllerMap.put("/taxi/editprofile", getBean(TaxiEditProfileController.class));


//        urlToControllerMap.put("/admin", getBean(AdminMenuController.class));
//        urlToControllerMap.put("/admin/adminAuthorization", getBean(AdminAuthorizationController.class));
//        urlToControllerMap.put("/admin/adminRegistration", getBean(AdminRegistrationController.class));
//        urlToControllerMap.put("/admin/adminEditProfile", getBean(AdminEditProfileController.class));
//        urlToControllerMap.put("/admin/signOut", getBean(AdminSignOutController.class));

        /*
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

        urlToControllerMap.put("/admin", new AdminMenuController());
        urlToControllerMap.put("/admin/adminAuthorization", new AdminAuthorizationController());
        urlToControllerMap.put("/admin/adminRegistration", new AdminRegistrationController());
        urlToControllerMap.put("/admin/adminEditProfile", new AdminEditProfileController());
        urlToControllerMap.put("/admin/signOut", new AdminSignOutController());
        */
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

    private MVCController getBean(Class clazz) {
        return (MVCController) springContext.getBean(clazz);
    }

}
