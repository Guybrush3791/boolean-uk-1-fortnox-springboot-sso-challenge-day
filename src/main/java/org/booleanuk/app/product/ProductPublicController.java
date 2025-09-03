package org.booleanuk.app.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/public/product")
public class ProductPublicController {

    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        List<ProductDto> productDtos = service.getAllProducts();
        return ResponseEntity.ok(productDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAProduct(@PathVariable int id) {
        return ResponseEntity.ok(service.getProduct(id));
    }

}
