package com.ecommerce.server.demo.repository;

import com.ecommerce.server.demo.dto.CategoryDto;
import com.ecommerce.server.demo.model.trg.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findProductByName(String name);

    @Query( value = "Select ctrg01_category AS categoryname," +
            "sum(ctrg01_quantity) AS productsavailable " +
            "from trg01_product group by ctrg01_category " +
            "order by ctrg01_category ASC",
            nativeQuery = true)
    Optional<CategoryDto> getCategories();

}
