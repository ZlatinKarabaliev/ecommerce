package com.ecommerce.server.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ProductDto{

    private Long id;

    @Length(min = 3, max = 100, message = "Product name should be between 3 and 200 symbols")
    @NotNull(message = "Product name is required.")
    private String name;

    @Length(min = 3, max = 200, message = "Length of category should be between 3 and 200 symbols")
    private String category;

    @Length(min = 3, max = 500, message = "Length of category should be between 3 and 500 symbols")
    private String description;

    @Min(value = 0, message = "Quantity cannot be less than 0")
    @Max(value = Integer.MAX_VALUE, message = "Quantity is too big")
    private int quantity;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @CreatedDate
    private LocalDate createdDate;


    @JsonIgnore
    @LastModifiedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate lastModifiedDate;


}
