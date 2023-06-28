package org.kainos.ea.db.OrderDao;

import org.kainos.ea.cli.Order;
import org.kainos.ea.cli.Product;
import org.kainos.ea.cli.ProductRequest;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ProductDao {
    private DatabaseConnector databaseConnector = new DatabaseConnector();
    public List<Product> getAllProducts() {
        try (Connection c = databaseConnector.getConnection()) {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("SELECT ProductId, Name, Description, Price  FROM Product;");
            List<Product> orderList = new ArrayList<>();
            while (rs.next())
            {
                Product order = new Product(
                        rs.getInt("ProductId"),
                        rs.getString("Name"),
                        rs.getString("Description"),
                        rs.getDouble("Price")
                );
                orderList.add(order);
            }
            return orderList;

        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
            return  null;
        }
    }
    public Product getProductById(int id) throws SQLException
    {
        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT ProductId, Name, Description, Price  FROM Product where ProductId = " + id);
        while (rs.next())
        {
            return  new Product(
                    rs.getInt("ProductId"),
                    rs.getString("Name"),
                    rs.getString("Description"),
                    rs.getDouble("Price")
            );

        }
        return  null;

    }
    public int createProduct(ProductRequest product) throws SQLException{
        Connection c = databaseConnector.getConnection();
        String insertStatement = "INSERT INTO Product (Name, Description, Price) VALUES (?,?,?)";
        PreparedStatement st = c.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);
        st.setString(1, product.getName());
        st.setString(2,product.getDescription());
        st.setDouble(3,product.getPrice());
        st.executeUpdate();


        ResultSet rs = st.getGeneratedKeys();
        if(rs.next())
        {
            return rs.getInt(1);
        }
        return  -1;
    }
    public void updateProduct(int id, ProductRequest product)   throws SQLException {
    Connection c = DatabaseConnector.getConnection();
    String updateStatement = "UPDATE Product SET Name = ?, Description = ?, Price = ? Where ProductId = ?";
    PreparedStatement st = c.prepareStatement(updateStatement);
    st.setString(1, product.getName());
    st.setString(2, product.getDescription());
    st.setDouble(3, product.getPrice());
    st.setInt(4, id);

    st.executeUpdate();
    }
    public  void DeleteProduct(int id) throws SQLException
    {
        Connection c = DatabaseConnector.getConnection();
        String deleteStatment = "DELETE FROM Product Where ProductId = ?";
        PreparedStatement st = c.prepareStatement(deleteStatment);
        st.setInt(1,id);
        st.executeUpdate();
    }

}
