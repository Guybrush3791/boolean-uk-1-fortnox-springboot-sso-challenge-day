package org.booleanuk.app.controller.prvte;

import org.booleanuk.app.model.dto.CustomerRequestDto;
import org.booleanuk.app.model.dto.CustomerResponseDto;
import org.booleanuk.app.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/private/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<CustomerResponseDto> getAll() {
        return customerService.getAll();
    }

    @GetMapping("/{id}")
    public CustomerResponseDto getById(@PathVariable int id) {
        return customerService.getById(id);
    }

    @PostMapping
    public CustomerResponseDto create(@RequestBody CustomerRequestDto request) {
        return customerService.create(request);
    }

    @PutMapping("/{id}")
    public CustomerResponseDto update(@PathVariable int id, @RequestBody CustomerRequestDto request) {
        return customerService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        customerService.delete(id);
    }
}
