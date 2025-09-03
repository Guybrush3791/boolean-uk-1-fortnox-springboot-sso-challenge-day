package org.booleanuk.app;

import org.booleanuk.app.customer.Customer;
import org.booleanuk.app.customer.CustomerRepository;
import org.booleanuk.app.order.Order;
import org.booleanuk.app.order.OrderRepository;
import org.booleanuk.app.product.Product;
import org.booleanuk.app.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/seed")
public class Utility {

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private ProductRepository productRepo;


    @GetMapping
    public void seed() {
        // Only seed if data is not present (idempotent)
        if (productRepo.count() > 0 || customerRepo.count() > 0 || orderRepo.count() > 0) {
            return;
        }

        // ---- Seed Products ----
        List<Product> products = productRepo.saveAll(Arrays.asList(
                new Product(0, "Coffee", 300, null),
                new Product(0, "Tea", 250, null),
                new Product(0, "Pastry", 450, null),
                new Product(0, "Sandwich", 700, null),
                new Product(0, "Juice", 400, null)
        ));

        // ---- Seed Customers ----
        List<Customer> customers = customerRepo.saveAll(Arrays.asList(
                new Customer(0, "Alice", "alice@example.com", null),
                new Customer(0, "Bob", "bob@example.com", null),
                new Customer(0, "Charlie", "charlie@example.com", null)
        ));

        // ---- Seed Orders ----
        Order order1 = new Order(0, "2025-09-03", 750, customers.get(0), List.of(products.get(0), products.get(1))); // Alice
        Order order2 = new Order(0, "2025-09-03", 1150, customers.get(1), List.of(products.get(2), products.get(3))); // Bob
        Order order3 = new Order(0, "2025-09-03", 700, customers.get(0), List.of(products.get(4), products.get(1))); // Alice

//        List<Order> savedOrders = orderRepo.saveAll(List.of(order1, order2, order3));

        // ---- Update Customer -> Orders
        customers.get(0).setOrderList(List.of(order1, order3));
        customers.get(1).setOrderList(List.of(order2));
//        customerRepo.saveAll(customers);

        // ---- Update Product -> Orders
        products.get(0).setOrderList(new ArrayList<>(List.of(order1)));
        products.get(1).setOrderList(new ArrayList<>(List.of(order1, order3)));
        products.get(2).setOrderList(new ArrayList<>(List.of(order2)));
        products.get(3).setOrderList(new ArrayList<>(List.of(order2)));
        products.get(4).setOrderList(new ArrayList<>(List.of(order3)));


        orderRepo.saveAll(List.of(order1, order2, order3));
        customerRepo.saveAll(customers);
        productRepo.saveAll(products);
    }
}
