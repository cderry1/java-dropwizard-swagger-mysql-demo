package org.kainos.ea.client;

public class InvalidOrderException extends Throwable
{
    public InvalidOrderException(String validation) {
        super(validation);
    }
}
