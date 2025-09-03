package org.booleanuk.app.model.service;

import org.booleanuk.app.model.dto.OrderRequestDto;
import org.booleanuk.app.model.dto.ResponseDto;
import org.booleanuk.app.model.dto.statistics.OrderTotalValueDto;
import org.booleanuk.app.model.exception.CustomerNotFoundException;
import org.booleanuk.app.model.exception.OrderCreationException;
import org.booleanuk.app.model.exception.OrderDeletionException;
import org.booleanuk.app.model.exception.OrderNotFoundException;
import org.booleanuk.app.model.jpa.Order;
import org.booleanuk.app.model.repository.CustomerRepository;
import org.booleanuk.app.model.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    // Methods
    // Get all orders
    public List<ResponseDto.OrderDto> getAll() {
        return orderRepository.findAll()
                .stream()
                .map(ResponseDto.OrderDto::new)
                .toList();
    }

    // Get order by ID
    public ResponseDto.OrderDto getById(int id) {
        return orderRepository.findById(id)
                .map(ResponseDto.OrderDto::new)
                .orElseThrow(
                        () -> new OrderNotFoundException("No order with that id was found.")
                );
    }

    // Save new order
    public ResponseDto.OrderDto save(OrderRequestDto orderRequestDto) {
        Order order = new Order(orderRequestDto);
        order.setCustomer(customerRepository.findById(orderRequestDto.getCustomerId()).orElseThrow(
                () -> new CustomerNotFoundException("No customer with that id was found.")
        ));
        try {
            order = orderRepository.save(order);
        } catch (Exception e) {
            throw new OrderCreationException("Could not create order, please check all required fields are correct.");
        }
        return new ResponseDto.OrderDto(order);
    }

    // Update an existing order
    public ResponseDto.OrderDto update(int id, OrderRequestDto orderRequestDto) {
        Order order = orderRepository.findById(id)
                .orElseThrow(
                        () -> new OrderNotFoundException("No order with that id was found.")
                );

        order.setCreatedAt(orderRequestDto.getCreatedAt());
        order.setTotalAmount(orderRequestDto.getTotalAmount());
        order.setCustomer(customerRepository.findById(orderRequestDto.getCustomerId()).orElseThrow(
                () -> new CustomerNotFoundException("No customer with that id was found.")
        ));
        try {
            order = orderRepository.save(order);
        } catch (Exception e) {
            throw new OrderCreationException("Could not update order, please check all required fields are correct.");
        }
        return new ResponseDto.OrderDto(order);
    }

    // Delete an order
    public ResponseDto.OrderDto delete(int id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(
                        () -> new OrderNotFoundException("No order with that id was found.")
                );
        ResponseDto.OrderDto dto = new ResponseDto.OrderDto(order);
        try {
            orderRepository.deleteById(id);
        } catch (Exception e) {
            throw new OrderDeletionException("Could not delete order, please try again later.");
        }
        return dto;
    }

    // Statistics
    // Get all orders with full value, ordered by value descending
    public OrderTotalValueDto getAllOrdersDesc() {
        List<Order> orders = orderRepository.findAll();
        orders.sort((o1, o2) -> Double.compare(o2.getTotalAmount(), o1.getTotalAmount()));

        double totalValue = orders.stream().mapToDouble(Order::getTotalAmount).sum();

        return new OrderTotalValueDto(
                orders.stream().map(ResponseDto.OrderDto::new).toList(),
                totalValue
        );
    }

}
