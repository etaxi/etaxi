package lv.etaxi.dto;

import lv.etaxi.entity.Customer;
import lv.etaxi.entity.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/** Проект etaxi
 *  Функции работы с DTO объектами
 */

@Component
public class СonvertorDTO {

    public CustomerDTO convertCustomerToDTO(Customer customer) {

           if (customer != null) {
               return new CustomerDTO(
                       customer.getCustomerId(),
                       customer.getName(),
                       customer.getPhone(),
                       customer.getPassword());
           }
           return null;
    }

    public Customer convertCustomerFromDTO(CustomerDTO customerDTO) {

        if (customerDTO != null) {
            return new Customer(
                    customerDTO.getCustomerId(),
                    customerDTO.getName(),
                    customerDTO.getPhone(),
                    customerDTO.getPassword());
        }
        return null;
    }

    public OrderDTO convertOrderToDTO(Order order) {

        if (order != null) {
            return new OrderDTO(
                    order.getOrderId(),
                    order.getCustomerId(),
                    order.getOrderedDateTime(),
                    order.getOrderStatus(),
                    order.getFromAdress(),
                    order.getToAdress(),
                    order.getTaxiId(),
                    order.getDistance(),
                    order.getPrice(),
                    order.getRate(),
                    order.getFeedback());
        }
        return null;
    }

    public Order convertOrderFromDTO(OrderDTO orderDTO) {

        if (orderDTO != null) {
            return new Order(
                    orderDTO.getOrderId(),
                    orderDTO.getCustomerId(),
                    null,
                    orderDTO.getOrderedDateTime(),
                    orderDTO.getOrderStatus(),
                    orderDTO.getFromAdress(),
                    orderDTO.getToAdress(),
                    orderDTO.getTaxiId(),
                    orderDTO.getDistance(),
                    orderDTO.getPrice(),
                    orderDTO.getRate(),
                    orderDTO.getFeedback());
        }
        return null;
    }

    public List<OrderDTO> convertOrderListToDTOList(List<Order> listOfOrders) {

        List<OrderDTO> listOfOrdersDTO = new ArrayList<OrderDTO>();
        for (Order order : listOfOrders) {
             listOfOrdersDTO.add(convertOrderToDTO(order));
        }
        return listOfOrdersDTO;
    }

}
