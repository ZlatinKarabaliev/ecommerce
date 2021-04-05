package com.ecommerce.server.demo.service.product;


import com.ecommerce.server.demo.dto.CategoryDto;
import com.ecommerce.server.demo.dto.ProductDto;
import com.ecommerce.server.demo.exception.ExistingProductException;
import com.ecommerce.server.demo.exception.NotEnoughQuantityException;
import com.ecommerce.server.demo.exception.ProductNotFoundException;
import com.ecommerce.server.demo.model.trg.Product;
import com.ecommerce.server.demo.repository.ProductRepository;
import com.ecommerce.server.demo.utils.ProductMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    private ProductRepository productRepository;
    private ProductMapper productMapper;


    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
    }

    @Override
    public Page findPaginated(int pageNo, int pageSize, String orderBy, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() :
                Sort.by(orderBy).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return productRepository.findAll(pageable);
    }

    @Override
    public Product createProduct(ProductDto productDto) {
        checkIfProductExists(productDto.getName());
        productDto.setCreatedDate(LocalDate.now());
        productDto.setLastModifiedDate(null);
        Product product = productMapper.convertToEntity(productDto);
        Product createdProduct = productRepository.save(product);

        log.info("Product is created " + createdProduct.toString());

        return createdProduct;
    }

    @Override
    public Product orderProduct(long id, int quantity) throws NotEnoughQuantityException {
        Product product = getProductById(id);
        if (product.getQuantity() - quantity < 0) {
            throw new NotEnoughQuantityException(id);
        }

        // boolean isSuccess = createOrder(id, quantity, product.getName());

        boolean isSuccess = true;
        if (isSuccess) {
            product.setQuantity(product.getQuantity() - quantity);
            Product updatedProduct = productRepository.save(product);
            log.info("Product quantity is updated" + updatedProduct.toString());
            return updatedProduct;
        }

        return null;
    }

    @Override
    public void checkIfProductExists(String name) {
        Optional<Product> existingProduct = productRepository.findProductByName(name);
        if (existingProduct.isPresent()) {
            throw new ExistingProductException(name);
        }

        log.info(existingProduct.toString());
    }

    @Override
    public void deleteProduct(long id) {
        Optional<Product> existingUser = productRepository.findById(id);

        if (existingUser.isPresent()) {
            productRepository.deleteById(id);
        } else {
            throw new ProductNotFoundException(id);
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> product = Optional.ofNullable(productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id)));

        return product.get();
    }

    @Override
    public List<ProductDto> getProductDto(List<Product> products) {
        return products.stream()
                .map(productMapper::convertToDto)
                .collect(Collectors.toList());
    }


    @Override
    public Product updateProduct(ProductDto update, long id) {
        Optional<Product> foundProduct = productRepository.findById(id);

        if (foundProduct.isPresent()) {
            if (!foundProduct.get().getName().equals(update.getName())) {
                checkIfProductExists(update.getName());
            }

            BeanUtils.copyProperties(update, foundProduct);

            update.setLastModifiedDate(LocalDate.now());

            update.setId(id);

            Product updatedProduct = productRepository.save(foundProduct.get());

            log.info("Product is updated " + updatedProduct.toString());

            return updatedProduct;
        } else {
//            throw new ProductNotFoundException(id);
            return null;
        }
    }

    @Override
    public Optional<CategoryDto> getCategories() {
        return productRepository.getCategories();
    }
}
