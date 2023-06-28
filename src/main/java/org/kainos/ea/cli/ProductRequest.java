package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductRequest {
    private String name;
    private  double price;
    private  String description;

    public ProductRequest(@JsonProperty("name") String name, @JsonProperty("description") String description, @JsonProperty("price") double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }




}
