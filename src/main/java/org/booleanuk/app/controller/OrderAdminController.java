package org.booleanuk.app.controller;

import org.booleanuk.app.model.dto.order.OrderRequestDto;
import org.booleanuk.app.model.dto.order.OrderResponseDto;
import org.booleanuk.app.model.pojo.Order;
import org.booleanuk.app.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderAdminController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Order> getAll(){
        return orderService.getAll();
    }
    @GetMapping("values")
    public List<OrderResponseDto> getFullValue() {
        return orderService.allOrdersWithValue();
    }
    @GetMapping("{id}")
    public Order getOrderById(@PathVariable long id) {
        return orderService.getById(id);
    }
    
    @PostMapping
    public Order createOrder(@RequestBody OrderRequestDto orderDto){
        return orderService.createOrder(orderDto);
    }
    @PutMapping("{id}")
    public Order updateOrder(@PathVariable long id, @RequestBody OrderRequestDto orderDto) {
        return orderService.updateOrder(id, orderDto);
    }
    @DeleteMapping("{id}")
    public Order deleteOrder(@PathVariable long id){
        return orderService.deleteOrder(id);
    }
    //create orders with value
}
