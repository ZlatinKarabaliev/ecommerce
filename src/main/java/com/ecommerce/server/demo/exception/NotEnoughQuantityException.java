package com.ecommerce.server.demo.exception;

public class NotEnoughQuantityException extends Throwable {
    public NotEnoughQuantityException(long id) {
        super(String.format(Exceptions.NOT_ENOUGH_QUANTITY, id));
    }
}
