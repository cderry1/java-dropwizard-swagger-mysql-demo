package org.kainos.ea.cli;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Order implements Comparable<Order>  {
    private int orderId;
    private int clientId;

    public Order(int orderId, int clientId, Date orderDate) {
        setOrderId(orderId);
        setClientId(clientId);
        setOrderDate(orderDate);
    }

    private Date orderDate;
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    @Override
    public  String toString()
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        return "OrderId: " + this.getOrderId() + "CustomerId: " + this.getClientId() + " OrderDate:" + df.format(this.getOrderDate()) ;
    }

    @Override
    public int compareTo(Order order) {
        return this.getOrderDate().compareTo(order.getOrderDate());
    }
}