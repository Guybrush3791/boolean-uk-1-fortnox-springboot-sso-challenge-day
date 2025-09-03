package org.booleanuk.app.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/private/order")
public class OrderPrivateController {

    @Autowired
    private OrderService service;

    // CRUD OPERATIONS //
    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderDto body) {
        OrderDto order = service.createOrder(body);
        return ResponseEntity.ok(order);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getOrder(@PathVariable int id) {
        OrderDto dto = service.getOrder(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateOrder(@PathVariable int id, @RequestBody OrderDto body) {
        OrderDto dto = service.updateOrderPrivate(id, body);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable int id) {
        OrderDto dto = service.deleteOrder(id);
        return ResponseEntity.ok(dto);
    }
    // CRUD OPERATIONS END //


    @GetMapping("/totals")
    public ResponseEntity<?> getTotals() {
        List<OrderDto> list = service.getOrdersTotalSorted();
        return ResponseEntity.ok(list);
    }

}
