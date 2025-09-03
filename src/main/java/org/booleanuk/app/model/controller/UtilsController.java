package org.booleanuk.app.model.controller;

import java.time.LocalDate;
import java.util.List;

import org.booleanuk.app.model.jpa.Customer;
import org.booleanuk.app.model.jpa.Order;
import org.booleanuk.app.model.jpa.Product;
import org.booleanuk.app.model.repository.CustomerRepository;
import org.booleanuk.app.model.repository.OrderRepository;
import org.booleanuk.app.model.repository.ProductRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/public/utils")
public class UtilsController {

    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public UtilsController(CustomerRepository customerRepository, OrderRepository orderRepository, ProductRepository productRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @GetMapping
    public String initializeDatabase() {
        customerRepository.deleteAll();
        orderRepository.deleteAll();
        productRepository.deleteAll();

        // Sample 3 customers
        var customer1 = new Customer("Alice Johnson", "alice@johnson.com");
        var customer2 = new Customer("Bob Smith", "bob@smith.com");
        var customer3 = new Customer("Charlie Brown", "charlie@brown.com");

        List.of(customer1, customer2, customer3).forEach(customerRepository::save);

        // Sample 5 products
        var product1 = new Product("High performance laptop", 1200.00);
        var product2 = new Product("Wireless mouse", 25.00);
        var product3 = new Product("Mechanical keyboard", 75.00);
        var product4 = new Product("HD monitor", 200.00);
        var product5 = new Product("USB-C hub", 45.00);

        List.of(product1, product2, product3, product4, product5).forEach(productRepository::save);

        // Sample 5 orders
        var order1 = new Order(LocalDate.now(), 1225.00, customer1, List.of(product1, product2));
        var order2 = new Order(LocalDate.now(), 75.00, customer2, List.of(product3));
        var order3 = new Order(LocalDate.now(), 245.00, customer3, List.of(product4, product5));
        var order4 = new Order(LocalDate.now(), 1300.00, customer1, List.of(product1, product3, product5));
        var order5 = new Order(LocalDate.now(), 25.00, customer2, List.of(product2));

        List.of(order1, order2, order3, order4, order5).forEach(orderRepository::save);

        return "Database initialized with sample data.";
    }
}
