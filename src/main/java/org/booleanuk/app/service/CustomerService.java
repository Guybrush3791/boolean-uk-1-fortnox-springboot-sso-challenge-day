package org.booleanuk.app.service;

import org.booleanuk.app.model.dto.customer.CustomerResponseDto;
import org.booleanuk.app.model.dto.customer.CustomerSummaryDto;
import org.booleanuk.app.model.dto.customer.CustomerValueDto;
import org.booleanuk.app.model.dto.customer.CustomerRequestDto;
import org.booleanuk.app.model.pojo.Customer;
import org.booleanuk.app.model.pojo.Order;
import org.booleanuk.app.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<CustomerSummaryDto> getAll() {
        return customerRepository.findAll().stream()
                .map(CustomerSummaryDto::new)
                .toList();
    }
    public CustomerResponseDto getById(long id) {
        return new CustomerResponseDto(customerRepository.findById(id).orElse(null));
    }
    public CustomerResponseDto createCustomer(CustomerRequestDto customerDto) {
        Customer customer = new Customer();
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customerRepository.save(customer);
        return new CustomerResponseDto(customer);
    }
    public CustomerResponseDto updateCustomer(long id, CustomerRequestDto customerDto) {
        Customer existingCustomer = customerRepository.findById(id).orElse(null);
        if (existingCustomer != null) {
            existingCustomer.setName(customerDto.getName());
            existingCustomer.setEmail(customerDto.getEmail());
            existingCustomer.setOrderList(new ArrayList<>());
            customerRepository.save(existingCustomer);
        }
        return new CustomerResponseDto(existingCustomer);
    }
    public CustomerResponseDto deleteCustomer(long id) {
        Customer existingCustomer = customerRepository.findById(id).orElse(null);
        if(existingCustomer!=null) {
            customerRepository.delete(existingCustomer);
        }
        return new CustomerResponseDto(existingCustomer);
    }
    public List<CustomerValueDto> calculateValue() {
           return customerRepository.findAll().stream().map(c -> {
               float sum = (float)c.getOrderList().stream().mapToDouble(Order::getTotalAmount).sum();
               return new CustomerValueDto(c.getId(), c.getName(), c.getOrderList().size(), sum);
           })
                   .toList();
    }
}
