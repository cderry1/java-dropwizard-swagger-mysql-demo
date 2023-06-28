package org.kainos.ea.api;

import org.kainos.ea.cli.Order;
import org.kainos.ea.cli.OrderRequest;
import org.kainos.ea.cli.Product;
import org.kainos.ea.cli.ProductRequest;
import org.kainos.ea.client.*;
import org.kainos.ea.core.OrderValidator;
import org.kainos.ea.db.OrderDao.OrderDao;

import java.sql.SQLException;
import java.util.*;

public class OrderService {
    private OrderDao orderDao = new OrderDao();
    private OrderValidator orderValidator = new OrderValidator();

    public List<Order> getAllOrders() throws FailedToGetOrdersException {
        List<Order> orderList = null;
        try {
            orderList = orderDao.getAllOrders();
            return orderList;
        } catch (SQLException e) {
            e.getMessage();
            throw new FailedToGetOrdersException();
        }

        //print All Orders
        // orderList.stream().forEach(System.out::println);


        //Sorts By Date because the Order Class has a compare function which will order by dates by DESC can add sorted
        //  Collections.sort(orderList);
        // orderList.stream().forEach(System.out::println);
        // orderList.stream().sorted().forEach(System.out::println);
        // Orders from last week only

        // Date date = new Date();
        //Calendar cal = new GregorianCalendar();
        //cal.add(Calendar.DAY_OF_MONTH);
        //Date oldDate = cal.getTime();
        // orderList.stream().filter(order -> order.getOrderDate().compareTo(date) > 7).forEach(System.out::println);


        // Orders from CustomerId 1 only
        //  Collections.sort(orderList);
        // orderList.stream().filter(order -> order.getCustomerId() == 1).forEach(System.out::println);


        // most recent Date
        //System.out.println("Max Order Date " + Collections.max(orderList));

        // Oldest Date
        //System.out.println("Min Order Date " + Collections.min(orderList));

        //Total Count of orders
        //     System.out.println("Total Orders" + orderList.size);

        //Client Most Orders

        //       Integer Max = orderList.stream().collect(Collectors.groupingBy(Order::getClientId, Collectors.counting())).entrySet().stream().max(Map.Entry.comparingByKey()).map(Map.Entry::getKey).orElse(other null);
        // System.out.println("Most Orders CustomerId is " + Max);
        //Client Least Orders
//        Integer Min = orderList.stream().collect(Collectors.groupingBy(Order::getClientId, Collectors.counting())).entrySet().stream().min(Map.Entry.comparingByKey()).map(Map.Entry::getKey).ifPresent(System.out::println);
        //System.out.println("Least Orders CustomerId is " + Min);
        // return orderList;
    }

    public Order getOrderById(int id) throws FailedToGetProductsException, ProductDoesNotExistException {
        try {
            Order order = orderDao.getOrderById(id);
            if (order == null) {
                throw new ProductDoesNotExistException();

            }
            return order;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToGetProductsException();
        }
    }

    public int createOrder(OrderRequest order) throws FailedToCreateOrderException, InvalidOrderException {
        try {
            int id = orderDao.createOrder(order);
            if (id == 1) {
                throw new FailedToCreateOrderException();

            }
            return id;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToCreateOrderException();
        }
    }

    public void updateOrder(int id, OrderRequest orderRequest) throws InvalidOrderException, OrderDoesNotExistException, FailedToUpdateOrderException, SQLException {
        try {
            String validation = orderValidator.isValidOrder(orderRequest);
            if (validation != null) {
                Order OrderToUp = orderDao.getOrderById(id);
                if (OrderToUp == null) {
                    throw new OrderDoesNotExistException();
                }
                orderDao.updateOrder(id, orderRequest);

            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToUpdateOrderException();
        } catch (FailedToGetOrdersException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteOrder(int id) throws OrderDoesNotExistException, FailedToDeleteOrderException
    {
        try
        {
            Order orderToDelete = orderDao.getOrderById(id);
            if(orderToDelete == null)
            {
                throw new OrderDoesNotExistException();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToDeleteOrderException();
        }
    }
}


