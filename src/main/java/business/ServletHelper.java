package business;

import entity.Order;
import java.util.List;

/** Проект etaxi
 * Created by D.Lazorkin on 25.03.2016.
 * Класс, содержащий вспомогательные функции для сервлетов
 */
public class ServletHelper {

    public static String generateHTMLTableForOrders(List<Order> listOfOrders,
                                                    boolean editOrders, boolean deleteOrders, boolean writeFeedback) {

        StringBuilder htmlString = new StringBuilder("<table border = 1 width=\"100%\">");
        htmlString.append("<tr>")
                .append("<th> ID </th>")
                .append("<th> Customer ID </th>")
                .append("<th> Date&Time </th>")
                .append("<th> Ordered Date&Time </th>")
                .append("<th> Status </th>")
                .append("<th> From address </th>")
                .append("<th> To address </th>")
                .append("<th> Taxi ID </th>")
                .append("<th> Distance </th>")
                .append("<th> Price </th>")
                .append("<th> Rate </th>")
                .append("<th> Feedback </th>");

                if (deleteOrders == true) htmlString.append("<th> Delete order </th>");
                if (editOrders == true) htmlString.append("<th> Edit order </th>");
                if (writeFeedback == true) htmlString.append("<th> Write feedback </th>");

                htmlString.append("</tr>");

        for (Order item : listOfOrders) {
            htmlString.append("<tr>")
                    .append("<td>").append(item.getOrderId())
                    .append("<td>").append(item.getCustomerId())
                    .append("<td>").append(item.getDateTime())
                    .append("<td>").append(item.getOrderedDateTime())
                    .append("<td>").append(item.getOrderStatus())
                    .append("<td>").append(item.getFromAdress())
                    .append("<td>").append(item.getToAdress())
                    .append("<td>").append(item.getTaxiId())
                    .append("<td>").append(item.getDistance())
                    .append("<td>").append(item.getPrice())
                    .append("<td>").append(item.getRate())
                    .append("<td>").append(item.getFeedback());

            if (deleteOrders == true)
                     htmlString.append("<td><form action='/customer/deleteOrderByCustomer' method='get'>" +
                                       "<input type='hidden' name='orderId' value='" + item.getOrderId() + "'/>" +
                                       "<input type='submit' value='Delete order'/></form>");

            if (editOrders == true)
                     htmlString.append("<td><form action='/customer/editOrderByCustomer' method='get'>" +
                                       "<input type='hidden' name='orderId' value='" + item.getOrderId() + "'/>" +
                                       "<input type='submit' value='Edit order'/></form>");

            if (writeFeedback == true)
                      htmlString.append("<td><form action='/customer/writeFeedback' method='get'>" +
                                       "<input type='hidden' name='orderId' value='" + item.getOrderId() + "'/>" +
                                       "<input type='submit' value='Write feedback'/></form>");

            htmlString.append("</tr>");
        }

        htmlString.append("</table>");

        return htmlString.toString();

    }

}
