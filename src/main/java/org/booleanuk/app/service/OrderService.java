package org.booleanuk.app.service;

import org.booleanuk.app.model.dto.order.OrderRequestDto;
import org.booleanuk.app.model.dto.order.OrderResponseDto;
import org.booleanuk.app.model.pojo.Order;
import org.booleanuk.app.model.pojo.Product;
import org.booleanuk.app.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAll() {
        return orderRepository.findAll();
    }
    public Order getById(long id) {
        return orderRepository.findById(id).orElse(null);
    }
    public Order createOrder(OrderRequestDto orderDto) {
        Order order = new Order();
        order.setCreatedAt(LocalDateTime.now());
        order.setTotalAmount(0);
        order.setCustomer(orderDto.getCustomer());
        order.setProductList(new ArrayList<>());
        order.setCustomer(orderDto.getCustomer());
        orderRepository.save(order);
        return order;
    }
    public Order updateOrder(long id, OrderRequestDto orderDto) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order != null) {
            order.setCreatedAt(orderDto.getCreatedAt());
            order.setTotalAmount((float)orderDto.getProductList().stream()
                    .mapToDouble(Product::getPrice)
                    .sum());
            order.setProductList(orderDto.getProductList());
            orderRepository.save(order);
        }
        return order;
    }
    public Order deleteOrder(long id) {
        Order existingOrder = orderRepository.findById(id).orElse(null);
        if(existingOrder!=null) {
            orderRepository.delete(existingOrder);
        }
        return existingOrder;
    }
    public List<OrderResponseDto> allOrdersWithValue() {
            return orderRepository.findAll().stream()
                    .map(OrderResponseDto::new)
                    .sorted((o1, o2)-> Float.compare(o1.getTotalAmount(),o2.getTotalAmount()))
                    .toList();
        }
        public float getTotalAmount(Order order) {
        return (float)order.getProductList().stream().mapToDouble(Product::getPrice).sum();
        }
    }

