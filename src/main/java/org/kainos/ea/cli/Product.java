package org.kainos.ea.cli;

import java.util.Date;

public class Product implements  Comparable<Product>{
    private int ProductId;
    private String name;
    private String Description;

    public Product(int productId, String name, String description, double price) {
        setProductId(productId);
        setName(name);
        setDescription(description);
        setPrice(price);
    }

    private double Price;
    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int orderId) {
        this.ProductId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    @Override
    public int compareTo(Product product) {
        return Double.compare(this.getPrice(), product.getPrice());
    }
    public  String toString()
    {
        return "ProductName: " +this.getName() + ", Product price: £ " + this.getPrice();
    }
}
