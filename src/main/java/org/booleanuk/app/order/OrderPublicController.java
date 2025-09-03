package org.booleanuk.app.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/order")
public class OrderPublicController {

    @Autowired
    private OrderService service;

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderDto dto) {
        return ResponseEntity.ok(service.createOrder(dto));
    }

    @PutMapping
    public ResponseEntity<?> updateOrder(@PathVariable int id, @RequestBody OrderDto dto) {
        OrderDto updatedDto = service.updateOrderPublic(id, dto);
        return ResponseEntity.ok(updatedDto);
    }

}
