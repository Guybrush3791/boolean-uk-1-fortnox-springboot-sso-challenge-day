package org.booleanuk.app.controller.prvte;

import org.booleanuk.app.model.dto.ProductRequestDto;
import org.booleanuk.app.model.dto.ProductResponseDto;
import org.booleanuk.app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/private/products")
public class ProductAdminController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductResponseDto> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public ProductResponseDto getById(@PathVariable int id) {
        return productService.getById(id);
    }

    @PostMapping
    public ProductResponseDto create(@RequestBody ProductRequestDto request) {
        return productService.create(request);
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable int id, @RequestBody ProductRequestDto request) {
        return productService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        productService.delete(id);
    }
}
