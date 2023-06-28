package org.kainos.ea.client;

public class OrderDoesNotExistException extends Throwable {
    @Override
    public  String getMessage()
    {
        return "This `Order` does not exist in this database";
    }
}
