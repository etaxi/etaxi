package lv.etaxi.MVC;

import lv.etaxi.MVC.MVCControllers.admin.*;
import lv.etaxi.MVC.MVCControllers.customer.*;
import lv.etaxi.MVC.MVCControllers.taxi.*;
import lv.etaxi.config.SpringAppConfig;
import org.json.JSONException;
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

    private ApplicationContext springContext;
    private Map<String, MVCController> urlToControllerMap;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        springContext = new AnnotationConfigApplicationContext(SpringAppConfig.class);
        urlToControllerMap = new HashMap<>();

        urlToControllerMap.put("/customer", getBean(CustomerMenuController.class));
        urlToControllerMap.put("/customer/getDistanceForOrder", getBean(CustomerOrderGetDistanceController.class));
        urlToControllerMap.put("/customer/customerAuthorization", getBean(CustomerAuthorizationController.class));
        urlToControllerMap.put("/customer/customerRegistration", getBean(CustomerRegistrationController.class));
        urlToControllerMap.put("/customer/customerEditProfile", getBean(CustomerEditProfileController.class));
        urlToControllerMap.put("/customer/customerCreateNewOrder", getBean(CustomerCreateNewOrderController.class));
        urlToControllerMap.put("/customer/customerHistoryOfOrders", getBean(CustomerHistoryOfOrdersController.class));
        urlToControllerMap.put("/customer/customerEditDeleteOrders", getBean(CustomerOrdersEditDeleteController.class));
        urlToControllerMap.put("/customer/customerDeleteOrder", getBean(CustomerOrderDeleteController.class));
        urlToControllerMap.put("/customer/customerEditOrder", getBean(CustomerOrderEditController.class));
        urlToControllerMap.put("/customer/customerWriteFeedbacksToOrders", getBean(CustomerWriteFeedbacksController.class));
        urlToControllerMap.put("/customer/writeFeedbackToOrder", getBean(CustomerWriteFeedbackController.class));
        urlToControllerMap.put("/customer/signOut", getBean(CustomerSignOutController.class));

        urlToControllerMap.put("/taxi", getBean(TaxiMenuController.class));
        urlToControllerMap.put("/taxi/registration", getBean(TaxiRegistrationController.class));
        urlToControllerMap.put("/taxi/authorization", getBean(TaxiAuthorizationController.class));
        urlToControllerMap.put("/taxi/logoff", getBean(TaxiLogoffController.class));
        urlToControllerMap.put("/taxi/history", getBean(TaxiHistoryController.class));
        urlToControllerMap.put("/taxi/openorders", getBean(TaxiOpenOrdersController.class));
        urlToControllerMap.put("/taxi/takeorder", getBean(TaxiTakeOrderController.class));
        urlToControllerMap.put("/taxi/completeorder", getBean(TaxiCompleteOrderController.class));
        urlToControllerMap.put("/taxi/cancelorder", getBean(TaxiCancelOrderController.class));
        urlToControllerMap.put("/taxi/editprofile", getBean(TaxiEditProfileController.class));

        urlToControllerMap.put("/admin", getBean(AdminMenuController.class));
        urlToControllerMap.put("/admin/adminAuthorization", getBean(AdminAuthorizationController.class));
        urlToControllerMap.put("/admin/adminRegistration", getBean(AdminRegistrationController.class));
        urlToControllerMap.put("/admin/adminEditProfile", getBean(AdminEditProfileController.class));
        urlToControllerMap.put("/admin/signOut", getBean(AdminSignOutController.class));
    }

    private MVCController getBean(Class clazz) {

        return (MVCController) springContext.getBean(clazz);
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
                } catch (JSONException e) {
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
