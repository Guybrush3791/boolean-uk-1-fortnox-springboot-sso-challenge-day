package org.booleanuk.app.model.service;

import org.booleanuk.app.model.dto.CustomerRequestDto;
import org.booleanuk.app.model.dto.ResponseDto;
import org.booleanuk.app.model.dto.statistics.CustomerValueDto;
import org.booleanuk.app.model.exception.CustomerCreationException;
import org.booleanuk.app.model.exception.CustomerDeletionException;
import org.booleanuk.app.model.exception.CustomerNotFoundException;
import org.booleanuk.app.model.jpa.Customer;
import org.booleanuk.app.model.jpa.Product;
import org.booleanuk.app.model.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Methods
    // Get all customers
    public List<ResponseDto.CustomerDto> getAll() {
        return customerRepository.findAll()
                .stream()
                .map(ResponseDto.CustomerDto::new)
                .toList();
    }

    // Get customer by ID
    public ResponseDto.CustomerDto getById(int id) {
        return customerRepository.findById(id)
                .map(ResponseDto.CustomerDto::new)
                .orElseThrow(
                        () -> new CustomerNotFoundException("No customer with that id was found.")
                );
    }

    // Save new customer
    public ResponseDto.CustomerDto save(CustomerRequestDto customerRequestDto) {
        Customer customer;
        try {
            customer = customerRepository.save(new Customer(customerRequestDto));
        } catch (Exception e) {
            throw new CustomerCreationException("Could not create customer, please check all required fields are correct.");
        }
        return new ResponseDto.CustomerDto(customer);
    }

    // Update an existing customer
    public ResponseDto.CustomerDto update(int id, CustomerRequestDto customerRequestDto) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(
                        () -> new CustomerNotFoundException("No customer with that id was found.")
                );

        customer.setName(customerRequestDto.getName());
        customer.setEmail(customerRequestDto.getEmail());
        try {
            customer = customerRepository.save(customer);
        } catch (Exception e) {
            throw new CustomerCreationException("Could not update customer, please check all required fields are correct.");
        }
        return new ResponseDto.CustomerDto(customer);
    }

    // Delete a customer
    public ResponseDto.CustomerDto delete(int id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(
                        () -> new CustomerNotFoundException("No customer with that id was found.")
                );
        ResponseDto.CustomerDto dto = new ResponseDto.CustomerDto(customer);
        try {
            customerRepository.delete(customer);
        } catch (Exception e) {
            throw new CustomerDeletionException("Could not delete customer, please try again later.");
        }
        return dto;
    }

    // Statistics
    // Calculate value for customer by ID (sum of order prices)
    public CustomerValueDto getCustomerValue(int id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(
                        () -> new CustomerNotFoundException("No customer with that id was found.")
                );
        double totalValue = customer.getOrders()
                .stream()
                .flatMap(order -> order.getProducts().stream())
                .mapToDouble(Product::getPrice)
                .sum();
        return new CustomerValueDto(customer, totalValue);
    }

}
