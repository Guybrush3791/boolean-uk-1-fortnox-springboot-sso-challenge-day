package org.booleanuk.app.model.controller;

import org.booleanuk.app.model.dto.ProductRequestDto;
import org.booleanuk.app.model.exception.ProductCreationException;
import org.booleanuk.app.model.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/products")
public class ProductAdminController {
    private final ProductService productService;

    public ProductAdminController(ProductService productService) {
        this.productService = productService;
    }

    // GET /api/products
    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.ok(productService.getAll());
    }

    // GET /api/products/{id}
    @GetMapping("{id}")
    public ResponseEntity<?> getProductById(@PathVariable int id) {
        return ResponseEntity.ok(productService.getById(id));
    }

    // POST /api/products
    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductRequestDto productRequestDto) {
        return ResponseEntity.status(201).body(productService.save(productRequestDto));
    }

    // PUT /api/products/{id}
    @PutMapping("{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id, @RequestBody ProductRequestDto productRequestDto) {
            return ResponseEntity.status(201).body(productService.update(id, productRequestDto));
    }

    // DELETE /api/products/{id}
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id) {
            return ResponseEntity.ok(productService.delete(id));
    }

    // GET /api/products/{id}/times-sold
    @GetMapping("{id}/times-sold")
    public ResponseEntity<?> getProductTimesSold(@PathVariable int id) {
            return ResponseEntity.ok(productService.getProductWithCount(id));
    }
}
