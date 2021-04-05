package com.ecommerce.server.demo.exception;

public class ProductNotFoundException extends RuntimeException
{
    public ProductNotFoundException(long id)
    {
        super(String.format(Exceptions.COULD_NOT_FIND_PRODUCT_WITH_ID, id));
    }
}
