package org.booleanuk.app.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/private/product")
public class ProductPrivateController {

    @Autowired
    private ProductService service;

    // CRUD OPERATIONS //
    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductDto body) {
        Product product = service.createProduct(body);
        return ResponseEntity.ok(product);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getProduct(@PathVariable int id) {
        ProductDto dto = service.getProduct(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id, @RequestBody ProductDto body) {
        ProductDto dto = service.updateProduct(id, body);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id) {
        ProductDto dto = service.deleteProduct(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/getNumSold")
    public ResponseEntity<?> getNumberOfSoldByProduct() {
        List<ProductDto> list = service.getNumberOfSoldForProduct();
        return ResponseEntity.ok(list);
    }

}
