package org.booleanuk.app.controller;

import org.booleanuk.app.model.dto.customer.CustomerResponseDto;
import org.booleanuk.app.model.dto.customer.CustomerSummaryDto;
import org.booleanuk.app.model.dto.customer.CustomerValueDto;
import org.booleanuk.app.model.dto.customer.CustomerRequestDto;
import org.booleanuk.app.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerAdminController {
    @Autowired
    private CustomerService customerService;
    
    @GetMapping
    public List<CustomerSummaryDto> getAllCustomers(){
        return customerService.getAll();
    }
    @GetMapping("values")
    public List<CustomerValueDto> getValues() {
        return customerService.calculateValue();
    }
    @GetMapping("{id}")
    public CustomerResponseDto getCustomerById(@PathVariable long id) {
        return customerService.getById(id);
    }
    @PostMapping
    public CustomerResponseDto createCustomer(@RequestBody CustomerRequestDto customerDto){
        return customerService.createCustomer(customerDto);
    }
    @PutMapping("{id}")
    public CustomerResponseDto updateCustomer(@PathVariable long id, @RequestBody CustomerRequestDto customerDto) {
        return customerService.updateCustomer(id, customerDto);
    }
    @DeleteMapping("{id}")
    public CustomerResponseDto deleteCustomer(@PathVariable long id){
        return customerService.deleteCustomer(id);
    }

}
