package org.booleanuk.app.customer;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/private/customer")
public class CustomerPrivateController {

    @Autowired
    private CustomerService service;

    // CRUD OPERATIONS //
    @PostMapping
    public ResponseEntity<?> createCustomer(@Valid @RequestBody CustomerDto body) {
        Customer customer = service.createCustomer(body);
        return ResponseEntity.ok(customer);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getCustomer(@PathVariable int id) {
        CustomerDto dto = service.getCustomer(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable int id, @RequestBody CustomerDto body) {
        CustomerDto dto = service.updateCustomer(id, body);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable int id) {
        CustomerDto dto = service.deleteCustomer(id);
        return ResponseEntity.ok(dto);
    }

    // END ---> OTHER OPS

    @GetMapping("/customerSum")
    public ResponseEntity<?> getSumOfCustomerOrders() {
        List<CustomerDto.OrderValueDto> list = service.getSumOfCustomerOrders();
        return ResponseEntity.ok(list);
    }


}
