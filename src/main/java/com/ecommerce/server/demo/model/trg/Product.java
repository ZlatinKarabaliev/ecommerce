package com.ecommerce.server.demo.model.trg;

import com.ecommerce.server.demo.model.AbstractDomainEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@Table(name = "trg01_product")
public class Product extends AbstractDomainEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ctrg01_id", unique = true, nullable = false, precision = 0)
    private Long id;

    @Length(min = 3, max = 100)
    @Column(name = "ctrg01_name")
    @NotNull(message = "Product name is required.")
    private String name;

    @Column(name = "ctrg01_category")
    private String category;

    @Column(name = "ctrg01_description")
    private String description;

    @Column(name = "ctrg01_quantity")
    @Min(0)
    private int quantity;
}
