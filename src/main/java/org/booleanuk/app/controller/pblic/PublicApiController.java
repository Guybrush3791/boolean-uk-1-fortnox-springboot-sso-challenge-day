package org.booleanuk.app.controller.pblic;

import org.booleanuk.app.model.dto.ChangeOrderRequestDto;
import org.booleanuk.app.model.dto.OrderRequestDto;
import org.booleanuk.app.model.dto.OrderResponseDto;
import org.booleanuk.app.model.dto.ProductResponseDto;
import org.booleanuk.app.service.OrderService;
import org.booleanuk.app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public")
public class PublicApiController {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;



    @GetMapping("/products")
    public List<ProductResponseDto> getAllProducts() {
        return productService.getAll();
    }

    @GetMapping("/products/{id}")
    public ProductResponseDto getProductById(@PathVariable int id) {
        return productService.getById(id);
    }

    @PostMapping("/orders")
    public OrderResponseDto createOrder(@RequestBody OrderRequestDto request) {
        return orderService.create(request);
    }

    @PatchMapping("/orders/{id}")
    public OrderResponseDto changeOrder(@PathVariable int id, @RequestBody ChangeOrderRequestDto request) {
        return orderService.update(id, request);
    }
}
