package com.ecommerce.server.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;


public interface CategoryDto  {
    String getCategoryName();
    Integer getProductsAvailable();
}
