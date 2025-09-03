package org.booleanuk.app.model.controller;

import org.booleanuk.app.model.dto.ResponseDto;
import org.booleanuk.app.model.exception.ProductNotFoundException;
import org.booleanuk.app.model.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/public/products")
public class ProductPublicController {
    private final ProductService productService;

    public ProductPublicController(ProductService productService) {
        this.productService = productService;
    }

    // GET /api/public/products
    @GetMapping
    public ResponseEntity<List<ResponseDto.ProductDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAll());
    }

    // GET /api/public/products/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable int id) {
        try {
            ResponseDto.ProductDto product = productService.getById(id);
            return ResponseEntity.ok(product);
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
