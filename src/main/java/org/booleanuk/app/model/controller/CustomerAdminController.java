package org.booleanuk.app.model.controller;

import org.booleanuk.app.model.dto.CustomerRequestDto;
import org.booleanuk.app.model.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/customers")
public class CustomerAdminController {
    private final CustomerService customerService;

    public CustomerAdminController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // GET /api/customers
    @GetMapping
    public ResponseEntity<?> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAll());
    }

    // GET /api/customers/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable int id) {
        return ResponseEntity.ok(customerService.getById(id));
    }

    // POST /api/customers
    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody CustomerRequestDto customerRequestDto) {
        return ResponseEntity.status(201).body(customerService.save(customerRequestDto));
    }

    // PUT /api/customers/{id}
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable int id, @RequestBody CustomerRequestDto customerRequestDto) {
            return ResponseEntity.status(201).body(customerService.update(id, customerRequestDto));
    }

    // DELETE /api/customers/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable int id) {
            return ResponseEntity.ok(customerService.delete(id));
    }

    // GET /api/customers/{id}/value
    @GetMapping("/{id}/value")
    public ResponseEntity<?> getCustomerTotalValue(@PathVariable int id) {
            return ResponseEntity.ok(customerService.getCustomerValue(id));
    }

}
