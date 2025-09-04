package org.booleanuk.app.controller;

import org.booleanuk.app.model.dto.product.ProductSalesStatisticsDto;
import org.booleanuk.app.model.dto.product.ProductRequestDto;
import org.booleanuk.app.model.pojo.Product;
import org.booleanuk.app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductAdminController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getAll();
    }
    @GetMapping("{id}")
    public Product getProductById(@PathVariable long id) {
        return productService.getById(id);
    }
    @PostMapping
    public Product createProduct(@RequestBody ProductRequestDto productDto){
        return productService.createProduct(productDto);
    }
    @PutMapping("{id}")
    public Product updateProduct(@PathVariable long id, @RequestBody ProductRequestDto productDto) {
        return productService.updateProduct(id, productDto);
    }
    @DeleteMapping("{id}")
    public Product deleteProduct(@PathVariable long id){
        return productService.deleteProduct(id);
    }
    @GetMapping("statistics/{id}")
    public ProductSalesStatisticsDto getProductWithStatistics(@PathVariable long id) {
        return productService.productWithSelLNumbers(id);
    }
}
