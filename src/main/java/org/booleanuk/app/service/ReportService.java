package org.booleanuk.app.service;

import org.booleanuk.app.model.dto.report.CustomerValueResponseDto;
import org.booleanuk.app.model.dto.report.OrderValueResponseDto;
import org.booleanuk.app.model.dto.report.ProductSalesResponseDto;
import org.booleanuk.app.model.jpa.Customer;
import org.booleanuk.app.model.jpa.Order;
import org.booleanuk.app.model.jpa.Product;
import org.booleanuk.app.model.repository.CustomerRepository;
import org.booleanuk.app.model.repository.OrderRepository;
import org.booleanuk.app.model.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private ProductRepository productRepo;

    // 1) Sum of order totals per customer
    public List<CustomerValueResponseDto> customerValues() {
        List<CustomerValueResponseDto> results = new ArrayList<>();
        List<Customer> customers = customerRepo.findAll();

        for (Customer c : customers) {
            BigDecimal total = BigDecimal.ZERO;
            for (Order o : c.getOrders()) {
                total = total.add(o.getTotalAmount());
            }
            results.add(new CustomerValueResponseDto(c.getId(), c.getName(), total));
        }

        return results;
    }

    // 2) Product with number of sell (count how many orders include the product)
    public List<ProductSalesResponseDto> productSales() {
        List<ProductSalesResponseDto> results = new ArrayList<>();
        List<Product> products = productRepo.findAll();

        for (Product p : products) {
            int count = p.getOrders().size(); // Many-to-Many without quantities
            results.add(new ProductSalesResponseDto(p.getId(), p.getName(), count));
        }

        return results;
    }

    // 3) All orders with full value, ordered by value (desc)
    public List<OrderValueResponseDto> ordersByValueDesc() {
        List<OrderValueResponseDto> results = new ArrayList<>();
        List<Order> orders = orderRepo.findAll();

        // Sort orders by totalAmount descending
        orders.sort(Comparator.comparing(Order::getTotalAmount).reversed());

        for (Order o : orders) {
            results.add(new OrderValueResponseDto(o.getId(), o.getCreatedAt(), o.getTotalAmount()));
        }

        return results;
    }
}
