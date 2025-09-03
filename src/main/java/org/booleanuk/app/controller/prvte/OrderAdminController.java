package org.booleanuk.app.controller.prvte;

import org.booleanuk.app.model.dto.ChangeOrderRequestDto;
import org.booleanuk.app.model.dto.OrderRequestDto;
import org.booleanuk.app.model.dto.OrderResponseDto;
import org.booleanuk.app.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/private/orders")
public class OrderAdminController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<OrderResponseDto> getAll() {
        return orderService.getAll();
    }

    @GetMapping("/{id}")
    public OrderResponseDto getById(@PathVariable int id) {
        return orderService.getById(id);
    }

    @PostMapping
    public OrderResponseDto create(@RequestBody OrderRequestDto request) {
        return orderService.create(request);
    }

    @PatchMapping("/{id}")
    public OrderResponseDto update(@PathVariable int id, @RequestBody ChangeOrderRequestDto request) {
        return orderService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        orderService.delete(id);
    }
}
