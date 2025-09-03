package org.booleanuk.app.service;

import org.booleanuk.app.model.dto.ProductRequestDto;
import org.booleanuk.app.model.dto.ProductResponseDto;
import org.booleanuk.app.model.jpa.Product;
import org.booleanuk.app.model.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public ProductResponseDto create(ProductRequestDto request) {
        Product toSave = new Product(request.name(), request.price());
        Product saved = productRepository.save(toSave);
        return toResponseDto(saved);
    }

    public List<ProductResponseDto> getAll() {
        List<Product> products = productRepository.findAll();
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();

        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            productResponseDtos.add(toResponseDto(p));
        }
        return productResponseDtos;
    }

    public ProductResponseDto getById(int id) {
        Product found = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found: " + id));
        return toResponseDto(found);
    }

    @Transactional
    public ProductResponseDto update(int id, ProductRequestDto request) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found: " + id));

        existing.setName(request.name());
        existing.setPrice(request.price());

        Product updated = productRepository.save(existing);
        return toResponseDto(updated);
    }

    @Transactional
    public void delete(int id) {
        if (!productRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found: " + id);
        }
        productRepository.deleteById(id);
    }

    private ProductResponseDto toResponseDto(Product product) {
        return new ProductResponseDto(product.getId(), product.getName(), product.getPrice());
    }
}
