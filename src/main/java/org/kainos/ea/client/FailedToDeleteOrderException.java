package org.kainos.ea.client;

public class FailedToDeleteOrderException extends  Throwable{
    @Override
    public  String getMessage()
    {
        return "Failed to Delete   order from the database";
    }
}
