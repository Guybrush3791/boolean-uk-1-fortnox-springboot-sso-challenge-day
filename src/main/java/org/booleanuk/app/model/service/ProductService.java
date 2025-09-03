package org.booleanuk.app.model.service;

import org.booleanuk.app.model.dto.ProductRequestDto;
import org.booleanuk.app.model.dto.ResponseDto;
import org.booleanuk.app.model.dto.statistics.ProductCountDto;
import org.booleanuk.app.model.exception.ProductCreationException;
import org.booleanuk.app.model.exception.ProductDeletionException;
import org.booleanuk.app.model.exception.ProductNotFoundException;
import org.booleanuk.app.model.jpa.Order;
import org.booleanuk.app.model.jpa.Product;
import org.booleanuk.app.model.repository.OrderRepository;
import org.booleanuk.app.model.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public ProductService(ProductRepository productRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    // Methods
    // Get all products
    public List<ResponseDto.ProductDto> getAll() {
        return productRepository.findAll()
                .stream()
                .map(ResponseDto.ProductDto::new)
                .toList();
    }

    // Get product by ID
    public ResponseDto.ProductDto getById(int id) {
        return productRepository.findById(id)
                .map(ResponseDto.ProductDto::new)
                .orElseThrow(
                        () -> new ProductNotFoundException("No product with that id was found.")
                );
    }

    // Save a new product
    public ResponseDto.ProductDto save(ProductRequestDto productRequestDto) {
        Product product;
        try {
                product = productRepository.save(new Product(productRequestDto));
            } catch (Exception e) {
                throw new ProductCreationException("Could not create product, please check all required fields are correct.");
        }
        return new ResponseDto.ProductDto(product);
    }

    // Update an existing product
    public ResponseDto.ProductDto update(int id, ProductRequestDto productRequestDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(
                        () -> new ProductNotFoundException("No product with that id was found.")
                );

        product.setName(productRequestDto.getName());
        product.setPrice(productRequestDto.getPrice());

        try {
            product = productRepository.save(product);
        } catch (Exception e) {
            throw new ProductCreationException("Could not update product, please check all required fields are correct.");
        }
        return new ResponseDto.ProductDto(product);
    }

    // Delete a product
    public ResponseDto.ProductDto delete(int id) {
        Product product = productRepository.findById(id)
                .orElseThrow(
                        () -> new ProductNotFoundException("No product with that id was found.")
                );
        ResponseDto.ProductDto dto = new ResponseDto.ProductDto(product);
        try {
            productRepository.delete(product);
        } catch (Exception e) {
            throw new ProductDeletionException("Could not delete product, please try again later.");
        }
        return dto;
    }

    // Statistics
    // Get a product and count of orders containing that product
    public ProductCountDto getProductWithCount(int id) {
        Product product = productRepository.findById(id)
                .orElseThrow(
                        () -> new ProductNotFoundException("No product with that id was found.")
                );

        long productCount = orderRepository.findAll().stream()
                .filter(o -> o.getProducts().stream()
                        .anyMatch(p -> p.getId() == id))
                .count();

        return new ProductCountDto(new ResponseDto.ProductDto(product), (int) productCount);
    }
}
