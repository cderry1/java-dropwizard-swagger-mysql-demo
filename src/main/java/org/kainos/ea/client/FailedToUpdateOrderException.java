package org.kainos.ea.client;

public class FailedToUpdateOrderException extends Throwable{
    @Override
    public  String getMessage()
    {
        return "Failed to update this order in the database";
    }
}
