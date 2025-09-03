package org.booleanuk.app.model.controller;

import org.booleanuk.app.model.dto.OrderRequestDto;
import org.booleanuk.app.model.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/public/orders")
public class OrderPublicController {
    private final OrderService orderService;

    public OrderPublicController(OrderService orderService) {
        this.orderService = orderService;
    }

    // POST /api/public/orders
    @PostMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        return ResponseEntity.status(201).body(orderService.save(orderRequestDto));
    }

    // PUT /api/public/orders/{id}
    @PutMapping("{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<?> updateOrder(@PathVariable int id, @RequestBody OrderRequestDto orderRequestDto) {
            return ResponseEntity.ok(orderService.update(id, orderRequestDto));
    }
}
