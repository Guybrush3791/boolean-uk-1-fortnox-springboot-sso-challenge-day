package org.booleanuk.app.service;

import org.booleanuk.app.model.dto.order.OrderRequestDto;
import org.booleanuk.app.model.dto.order.OrderResponseDto;
import org.booleanuk.app.model.pojo.Customer;
import org.booleanuk.app.model.pojo.Order;
import org.booleanuk.app.model.pojo.Product;
import org.booleanuk.app.repository.CustomerRepository;
import org.booleanuk.app.repository.OrderRepository;
import org.booleanuk.app.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public Order getById(long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order createOrder(OrderRequestDto orderDto) {
        Customer customer = customerRepository.findById(orderDto.getCustomer_id()).orElse(null);
        if (customer != null) {
            Order order = new Order();
            List<Product> products = new ArrayList<>();
            float totalAmount = 0;
            if (orderDto.getProductList() != null) {
                for (Long product_id : orderDto.getProductList()) {
                    Product product = productRepository.findById(product_id).orElse(null);
                    if (product != null) {
                        products.add(product);
                        totalAmount += product.getPrice();
                    }
                }
                order.setCreatedAt(LocalDateTime.now());
                order.setTotalAmount(totalAmount);
                order.setProductList(products);
                order.setCustomer(customer);
                orderRepository.save(order);
                return order;
            }
        }
        return null;
    }
    public Order updateOrder(long id, OrderRequestDto orderDto) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order != null) {
            Customer customer = customerRepository.findById(orderDto.getCustomer_id()).orElse(null);
            if (customer != null) {
                List<Product> products = new ArrayList<>();
                float totalAmount = 0;
                if (orderDto.getProductList() != null) {
                    for (Long product_id : orderDto.getProductList()) {
                        Product product = productRepository.findById(product_id).orElse(null);
                        if (product != null) {
                            products.add(product);
                            totalAmount += product.getPrice();
                        }
                    }
                    order.setCreatedAt(LocalDateTime.now());
                    order.setTotalAmount(totalAmount);
                    order.setProductList(products);
                    order.setCustomer(customer);
                    orderRepository.save(order);
                }
            }
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

