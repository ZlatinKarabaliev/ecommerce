package com.ecommerce.server.demo.controller;

import com.ecommerce.server.demo.dto.CategoryDto;
import com.ecommerce.server.demo.dto.ProductDto;
import com.ecommerce.server.demo.exception.NotEnoughQuantityException;
import com.ecommerce.server.demo.exception.ProductNotFoundException;
import com.ecommerce.server.demo.model.trg.Product;
import com.ecommerce.server.demo.service.product.ProductService;
import com.ecommerce.server.demo.utils.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Optional;

@RestController
@RequestMapping("product")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper mapper;

    @Autowired
    public ProductController(ProductService productService, ProductMapper mapper) {
        this.productService = productService;
        this.mapper = mapper;
    }


    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok().body(productService.getAllProducts());
    }

    @GetMapping("/paged")
    public Page getProducts(
            @RequestParam(defaultValue = "1") int pageNumber
            , @RequestParam(defaultValue = "10") int pageSize
            , @RequestParam(defaultValue = "name") String orderBy
            , @RequestParam(defaultValue = "desc") String sortDirection
    ) {
        return productService.findPaginated(pageNumber, pageSize, orderBy, sortDirection);
    }

    @PostMapping()
    @ResponseBody
    public ResponseEntity<?> saveProduct(@RequestBody ProductDto newProduct) {
        Product product = productService.createProduct(newProduct);

        if (product == null) {
            return ResponseEntity.badRequest().body( new ProductNotFoundException(newProduct.getId()));
        }
        return ResponseEntity.ok(product);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable long id, @Valid @RequestBody ProductDto updateProduct) throws ProductNotFoundException {

        Product product = productService.updateProduct(updateProduct, id);
        ProductDto productDto = mapper.convertToDto(product);
        return ResponseEntity.ok(productDto);
    }


    @GetMapping("/categories")
    public ResponseEntity<?> getCategories() {
        Optional<CategoryDto> categoriesList = productService.getCategories();
        return ResponseEntity.ok(categoriesList);
    }

    @PostMapping("/{id}/order/{orderQuantity}")
    public ResponseEntity<?> orderProduct(@PathVariable long id, @Valid @PathVariable @Min(1) int orderQuantity) throws NotEnoughQuantityException {
        Product product = productService.orderProduct(id, orderQuantity);

        ProductDto prdDto = (mapper.convertToDto(product));

        return ResponseEntity.ok(prdDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable(value = "id") long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }


}
