package org.booleanuk.app.service;

import org.booleanuk.app.model.dto.*;
import org.booleanuk.app.model.jpa.Customer;
import org.booleanuk.app.model.jpa.Product;
import org.booleanuk.app.model.repository.CustomerRepository;
import org.booleanuk.app.model.repository.OrderRepository;
import org.booleanuk.app.model.repository.ProductRepository;
import org.booleanuk.app.model.jpa.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public OrderResponseDto create(OrderRequestDto request) {
        Customer customer = customerRepository.findById(request.customerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Customer not found" + request.customerId()));
        List<Product> products = productRepository.findAllById(request.productIds());
        if (products.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "at least one valid product id is required");
        }

        BigDecimal total = calcTotal(products);

        Order order = new Order(
                LocalDateTime.now(),
                total,
                customer,
                new ArrayList<>(products)
        );

        Order saved = orderRepository.save(order);
        return toResponseDto(saved);
    }

    public List<OrderResponseDto> getAll() {
        List<Order> orders = orderRepository.findAll();
        List<OrderResponseDto> ResponseDtos = new ArrayList<>();

        for (int i = 0; i < orders.size(); i++) {
            Order o = orders.get(i);
            ResponseDtos.add(toResponseDto(o));
        }
        return ResponseDtos;

    }

    public OrderResponseDto getById(int id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found: " + id));
        return toResponseDto(order);
    }

    @Transactional
    public OrderResponseDto update(int orderId, ChangeOrderRequestDto request) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found: " + orderId));

        if (request.productIdsToAdd() != null && !request.productIdsToAdd().isEmpty()) {
            List<Product> toAdd = productRepository.findAllById(request.productIdsToAdd());
            order.getProducts().addAll(toAdd);
        }

        if (request.productIdsToRemove() != null && !request.productIdsToRemove().isEmpty()) {
            List<Product> toRemove = productRepository.findAllById(request.productIdsToRemove());
            order.getProducts().removeAll(toRemove);
        }

        order.setTotalAmount(calcTotal(order.getProducts()));

        Order saved = orderRepository.save(order);
        return toResponseDto(saved);
    }

    @Transactional
    public void delete(int id) {
        if (!orderRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found: " + id);
        }
        orderRepository.deleteById(id);
    }

    private BigDecimal calcTotal(List<Product> products) {
        BigDecimal total = BigDecimal.ZERO;

        for (Product product : products) {
            if (product.getPrice() != null) {
                total = total.add(product.getPrice());
            }
        }
        return total;
    }

    private OrderResponseDto toResponseDto(Order o) {
        Customer c = o.getCustomer();

        CustomerResponseDto customerDto = new CustomerResponseDto(
                c.getId(),
                c.getName(),
                c.getEmail()
        );

        List<ProductResponseDto> productDtos = new ArrayList<>();

        for (Product p : o.getProducts()) {
            ProductResponseDto dto = new ProductResponseDto(
                    p.getId(),
                    p.getName(),
                    p.getPrice()
            );
            productDtos.add(dto);
        }

        return new OrderResponseDto(
                o.getId(),
                o.getCreatedAt(),
                o.getTotalAmount(),
                customerDto,
                productDtos
        );
    }



}
