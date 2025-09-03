package org.booleanuk.app.controller;

import org.booleanuk.app.model.dto.order.OrderRequestDto;
import org.booleanuk.app.model.pojo.Order;
import org.booleanuk.app.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public/orders")
public class OrderPublicController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public Order createCustomerOrder(@RequestBody OrderRequestDto orderDto){
        return orderService.createOrder(orderDto);
    }
    @PutMapping("{id}")
    public Order updateCustomerOrder(@PathVariable long id, @RequestBody OrderRequestDto orderDto) {
        return orderService.updateOrder(id, orderDto);
    }
}
