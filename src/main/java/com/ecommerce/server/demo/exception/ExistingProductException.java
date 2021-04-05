package com.ecommerce.server.demo.exception;

public class ExistingProductException extends RuntimeException
{
    public ExistingProductException(String name)
    {
        super(String.format(Exceptions.EXISTING_PRODUCT_EXCEPTION, name));
    }
}
