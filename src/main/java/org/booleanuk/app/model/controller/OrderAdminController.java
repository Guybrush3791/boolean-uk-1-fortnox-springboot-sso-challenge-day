package org.booleanuk.app.model.controller;

import jakarta.transaction.Transactional;
import org.booleanuk.app.model.dto.OrderRequestDto;
import org.booleanuk.app.model.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/orders")
public class OrderAdminController {
    private final OrderService orderService;

    public OrderAdminController(OrderService orderService) {
        this.orderService = orderService;
    }

    // GET /api/orders
    @GetMapping
    public ResponseEntity<?> getAllOrders() {
        return ResponseEntity.ok(orderService.getAll());
    }

    // GET /api/orders/{id}
    @GetMapping("{id}")
    public ResponseEntity<?> getOrderById(@PathVariable int id) {
        return ResponseEntity.ok(orderService.getById(id));
    }

    // POST /api/orders
    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        return ResponseEntity.status(201).body(orderService.save(orderRequestDto));
    }

    // PUT /api/orders/{id}
    @PutMapping("{id}")
    public ResponseEntity<?> updateOrder(@PathVariable int id, @RequestBody OrderRequestDto orderRequestDto) {
            return ResponseEntity.status(201).body(orderService.update(id, orderRequestDto));
    }

    // DELETE /api/orders/{id}
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable int id) {
            return ResponseEntity.ok(orderService.delete(id));
    }

    // GET /api/orders/ordered-value
    @GetMapping("ordered-value")
    public ResponseEntity<?> getOrdersByValue() {
        return ResponseEntity.ok(orderService.getAllOrdersDesc());
    }

}
