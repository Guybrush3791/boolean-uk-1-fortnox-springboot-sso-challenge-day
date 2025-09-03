package org.booleanuk.app.controller.seed;

import org.booleanuk.app.model.jpa.Order;

import org.booleanuk.app.model.jpa.Customer;
import org.booleanuk.app.model.jpa.Product;
import org.booleanuk.app.model.repository.CustomerRepository;
import org.booleanuk.app.model.repository.OrderRepository;
import org.booleanuk.app.model.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class SeedData {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/seed")
    public String seedData() {
        customerRepository.deleteAll();
        productRepository.deleteAll();
        orderRepository.deleteAll();

        // Customers
        Customer cust1 = new Customer("Alice Johnson", "alice@example.com");
        Customer cust2 = new Customer("Bob Smith", "bob@example.com");
        Customer cust3 = new Customer("Carol Davis", "carol@example.com");
        customerRepository.saveAll(List.of(cust1, cust2, cust3));

        // Products
        Product p1 = new Product("Laptop", new BigDecimal("1200.00"));
        Product p2 = new Product("Smartphone", new BigDecimal("800.00"));
        Product p3 = new Product("Tablet", new BigDecimal("600.00"));
        Product p4 = new Product("Monitor", new BigDecimal("300.00"));
        Product p5 = new Product("Keyboard", new BigDecimal("100.00"));
        productRepository.saveAll(List.of(p1, p2, p3, p4, p5));

        // Orders
        Order order1 = new Order(LocalDateTime.now(), p1.getPrice().add(p5.getPrice()), cust1, List.of(p1, p5));
        Order order2 = new Order(LocalDateTime.now(), p2.getPrice().add(p3.getPrice()), cust1, List.of(p2, p3));
        Order order3 = new Order(LocalDateTime.now(), p4.getPrice(), cust2, List.of(p4));

        cust1.getOrders().add(order1);
        cust1.getOrders().add(order2);
        cust2.getOrders().add(order3);

        orderRepository.saveAll(List.of(order1, order2, order3));

        return "Seeded 5 products, 3 customers, and 3 orders by 2 customers";
    }
}
