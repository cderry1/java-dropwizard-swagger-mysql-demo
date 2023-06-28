package org.kainos.ea.client;

public class ProductDoesNotExistException extends Throwable {
    @Override
    public  String getMessage()
    {
        return "This Product does not exist in this database";
    }
}
