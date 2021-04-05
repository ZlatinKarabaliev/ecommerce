package com.ecommerce.server.demo.service.product;

import com.ecommerce.server.demo.dto.CategoryDto;
import com.ecommerce.server.demo.dto.ProductDto;
import com.ecommerce.server.demo.exception.NotEnoughQuantityException;
import com.ecommerce.server.demo.model.trg.Product;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Page findPaginated(int pageNumber, int pageSize, String orderBy, String sortDirection);

    Product createProduct(ProductDto newProduct);

    List<Product> getAllProducts();

    Product orderProduct(long id, int quantity) throws NotEnoughQuantityException;

    void checkIfProductExists(String name);

    void deleteProduct(long id);

    Product getProductById(Long id);

    List<ProductDto> getProductDto(List<Product> products);

    Product updateProduct(ProductDto update, long id);

    Optional<CategoryDto> getCategories();
}
