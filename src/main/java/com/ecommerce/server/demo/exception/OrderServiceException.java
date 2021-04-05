package com.ecommerce.server.demo.exception;

public class OrderServiceException extends RuntimeException
{
    public OrderServiceException()
    {
        super(Exceptions.ORDER_SERVICE_IS_UNAVAILABLE_PLEASE_TRY_AGAIN_LATER);
    }
}
