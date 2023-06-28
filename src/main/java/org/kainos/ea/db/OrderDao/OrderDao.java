package org.kainos.ea.db.OrderDao;

import org.kainos.ea.cli.Order;
import org.kainos.ea.cli.OrderRequest;
import org.kainos.ea.cli.Product;
import org.kainos.ea.cli.ProductRequest;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class OrderDao {
    private DatabaseConnector databaseConnector = new DatabaseConnector();

    public List<Order> getAllOrders() throws SQLException {
        try (Connection c = databaseConnector.getConnection()) {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("SELECT OrderId, CustomerId, OrderDate FROM `Order`;");
            List<Order> orderList = new ArrayList<>();
            while (rs.next()) {
                Order order = new Order(
                        rs.getInt("OrderId"),
                        rs.getInt("CustomerId"),
                        rs.getDate("OrderDate")
                );
                orderList.add(order);
            }
            return orderList;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public Order getOrderById(int id) throws SQLException {
        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT OrderId, CustomerId, OrderDate FROM `Order` where OrderId = " + id);
        while (rs.next()) {
           return new Order(
                    rs.getInt("OrderId"),
                    rs.getInt("CustomerId"),
                    rs.getDate("OrderDate")
            );

        }
        return null;
    }
    public int createOrder(OrderRequest order) throws SQLException{
        Connection c = databaseConnector.getConnection();
        String insertStatement = "INSERT INTO `Order` (ClientId, OrderDate, DispatchDate) VALUES (?,?,?)";
        PreparedStatement st = c.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);
        st.setInt(1, order.getClientId());
        st.setDate(2,order.getStartDate());
        st.setDate(3,order.getStartDate());
        st.executeUpdate();


        ResultSet rs = st.getGeneratedKeys();
        if(rs.next())
        {
            return rs.getInt(1);
        }
        return  -1;
    }
    public void updateOrder(int id, OrderRequest orderRequest)   throws SQLException {
        Connection c = DatabaseConnector.getConnection();
        String updateStatement = "UPDATE `Order` SET ClientId = ?, OrderDate = ?, DispatchDate = ? Where OrderId = ?";
        PreparedStatement st = c.prepareStatement(updateStatement);
        st.setInt(1, orderRequest.getClientId());
        st.setDate(2, orderRequest.getStartDate());
        st.setDate(3, orderRequest.getEndDate());
        st.setInt(4, id);

        st.executeUpdate();
    }
    public  void DeleteOrder(int id) throws SQLException
    {
        Connection c = DatabaseConnector.getConnection();
        String deleteStatement = "DELETE FROM `Order` Where OrderId = ?";
        PreparedStatement st = c.prepareStatement(deleteStatement);
        st.setInt(1,id);
        st.executeUpdate();
    }
}
