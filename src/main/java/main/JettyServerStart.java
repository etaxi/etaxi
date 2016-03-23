package main;

import accounts.AccountService;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.*;
import servlets.customer.ServletListOfCustomers;
import servlets.customer.ServletNewCustomer;
import servlets.customer.ServletNewOrder;
import servlets.customer.SessionsServletForCustomer;

public class JettyServerStart {

    public static void main(String[] args) throws Exception {

        AccountService accountService = new AccountService();

        int port = 8080;

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        //context.addServlet(new ServletHolder(new UsersServlet(accountService)), "/users");
        context.addServlet(new ServletHolder(new SessionsServletForCustomer()), "/signinCustomer.html");

        context.addServlet(new ServletHolder(new ServletNewCustomer()), "/customer.html");      // http://localhost:8080/customer.html
        context.addServlet(new ServletHolder(new ServletListOfCustomers()), "/customers.html"); // http://localhost:8080/customers.html

        context.addServlet(new ServletHolder(new ServletNewTaxi()), "/taxi.html");      // http://localhost:8080/taxi.html
        context.addServlet(new ServletHolder(new ServletListOfTaxi()), "/taxis.html");  // http://localhost:8080/taxis.html

        context.addServlet(new ServletHolder(new ServletNewOrder()), "/order.html");       // http://localhost:8080/order.html
        context.addServlet(new ServletHolder(new ServletListOfOrders()), "/orders.html");  // http://localhost:8080/orders.html

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setResourceBase("templates");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});

        Server server = new Server(8080);
        System.out.println("Listening port: " + port);
        server.setHandler(handlers);

        server.start();
        server.join();

    }

}
