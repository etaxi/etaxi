package main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.ServletListOfCustomers;
import servlets.ServletNewCustomer;
import servlets.SrvltCustomers;

public class Main {

	public static void main(String[] args) throws Exception {

        int port = 8080;

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.addServlet(new ServletHolder(new SrvltCustomers()), "/func");  // http://localhost:8080/func

        context.addServlet(new ServletHolder(new ServletNewCustomer()), "/customer.html");  // http://localhost:8080/customer.html

        context.addServlet(new ServletHolder(new ServletListOfCustomers()), "/customers.html");  // http://localhost:8080/customers.html

        Server server = new Server(port);
        server.setHandler(context);

        server.start();
        System.out.println("Listening port: " + port);
        server.join();

	}

}
