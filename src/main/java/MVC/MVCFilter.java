package MVC;

import MVC.MVCControllers.customer.HelloWorldController;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        urlToControllerMap.put("/hello", new HelloWorldController());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String contextURL = request.getServletPath();

        if (contextURL.equals("/index.html")) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else {

            MVCController controller = urlToControllerMap.get(contextURL);
            MVCModel model = controller.handleRequest(request);

            request.setAttribute("model", model.getData());

            ServletContext context = request.getServletContext();
            RequestDispatcher requestDispatcher = context.getRequestDispatcher(model.getJspName());
            requestDispatcher.forward(request, response);
        }

    }

    @Override
    public void destroy() {
    }

}
