package org.kainos.ea.client;

public class FailedToCreateProductException extends Throwable {
    @Override
    public  String getMessage()
    {
        return "Failed to create new  product to the database";
    }
}
