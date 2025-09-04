package org.booleanuk.app.controller;

import org.booleanuk.app.model.pojo.Product;
import org.booleanuk.app.service.ProductService;
import org.booleanuk.app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/public/products")
public class ProductPublicController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllPublicProducts(){
        return productService.getAll();
    }
    @GetMapping("{id}")
    public Product getPublicProductById(@PathVariable long id) {
        return productService.getById(id);
    }
}
