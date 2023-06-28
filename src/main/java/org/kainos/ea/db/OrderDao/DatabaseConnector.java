package org.kainos.ea.db.OrderDao;

import org.kainos.ea.cli.Order;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DatabaseConnector {
    private static Connection conn;

    public static Connection getConnection() throws SQLException {
        String user, password, host, name;
        if (conn != null && !conn.isClosed()) {
            return conn;
        }
        try (
            FileInputStream propsStream = new FileInputStream("db.properties"))
        {
            Properties prop = new Properties();
            prop.load(propsStream);
            user = prop.getProperty("user");
            password = prop.getProperty("password");
            host = prop.getProperty("host");
            name = prop.getProperty("name");
            if (user == null || password == null || host == null || name == null)
                throw new IllegalArgumentException("Properties file must exist " +
                        "must contain user, password, host and name properties");
            conn = DriverManager.getConnection("jdbc:mysql://" + host + "/" + name + "?useSSL=false", user, password);
            propsStream.close();
            return conn;

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        finally{
            System.out.println("I will always run");
        }

        return null;

    }
}