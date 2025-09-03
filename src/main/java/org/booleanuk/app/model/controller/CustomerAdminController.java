package org.booleanuk.app.model.controller;

import org.booleanuk.app.model.dto.CustomerRequestDto;
import org.booleanuk.app.model.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/customers")
public class CustomerAdminController {
    private final CustomerService customerService;

    public CustomerAdminController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /*
    I am aware that it is probably not good practice to have duplicate endpoints for admin and public controllers,
    but I wrote them out because the challenge requirements specified all CRUD operations for admin.
     */
    // GET /api/customers
    @GetMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<?> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAll());
    }

    // GET /api/customers/{id}
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<?> getCustomerById(@PathVariable int id) {
        return ResponseEntity.ok(customerService.getById(id));
    }

    // POST /api/customers
    @PostMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<?> createCustomer(@RequestBody CustomerRequestDto customerRequestDto) {
        return ResponseEntity.status(201).body(customerService.save(customerRequestDto));
    }

    // PUT /api/customers/{id}
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<?> updateCustomer(@PathVariable int id, @RequestBody CustomerRequestDto customerRequestDto) {
            return ResponseEntity.status(201).body(customerService.update(id, customerRequestDto));
    }

    // DELETE /api/customers/{id}
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<?> deleteCustomer(@PathVariable int id) {
            return ResponseEntity.ok(customerService.delete(id));
    }

    // Statistics
    // GET /api/customers/{id}/value
    @GetMapping("/{id}/value")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getCustomerTotalValue(@PathVariable int id) {
            return ResponseEntity.ok(customerService.getCustomerValue(id));
    }

}
