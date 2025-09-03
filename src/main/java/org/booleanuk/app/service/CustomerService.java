package org.booleanuk.app.service;

import org.booleanuk.app.model.dto.CustomerRequestDto;
import org.booleanuk.app.model.dto.CustomerResponseDto;
import org.booleanuk.app.model.jpa.Customer;
import org.booleanuk.app.model.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

@Transactional
public CustomerResponseDto create(CustomerRequestDto request) {
    Customer toSave = new Customer(request.name(), request.email());
    Customer saved = customerRepository.save(toSave);
    return toResponseDto(saved);
}

    public List<CustomerResponseDto> getAll() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerResponseDto> responseDtos = new ArrayList<>();

        for (int i = 0; i < customers.size(); i++) {
            Customer c = customers.get(i);
            responseDtos.add(toResponseDto(c));
        }

        return responseDtos;
    }

    public CustomerResponseDto getById(int id) {
        Customer found = customerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Customer not found: " + id));
        return toResponseDto(found);
    }

    @Transactional
    public CustomerResponseDto update(int id, CustomerRequestDto request) {
        Customer existing = customerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Customer not found: " + id));
        existing.setName(request.name());
        existing.setEmail(request.email());

        Customer updated = customerRepository.save(existing);
        return toResponseDto(updated);
    }

    @Transactional
    public CustomerResponseDto delete(int id) {
        Customer toDelete = customerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Customer not found: " + id));
        customerRepository.delete(toDelete);
        return toResponseDto(toDelete);
    }

    private CustomerResponseDto toResponseDto(Customer customer) {
        return new CustomerResponseDto(customer.getId(), customer.getName(), customer.getEmail());
    }
}
