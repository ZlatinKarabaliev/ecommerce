package com.ecommerce.server.demo.exception;

public interface Exceptions
{
    String EXISTING_PRODUCT_EXCEPTION = "Product with name %s already in the database";
    String COULD_NOT_FIND_PRODUCT_WITH_ID = "Could not find product with id %d!";
    String NOT_ENOUGH_QUANTITY = "Quantity of the product with id %d is not enough!";
    String ORDER_SERVICE_IS_UNAVAILABLE_PLEASE_TRY_AGAIN_LATER = "Order-service is unavailable, please try again later.";
}
