package org.booleanuk.app.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.booleanuk.app.model.jpa.Customer;
import org.booleanuk.app.model.jpa.Order;
import org.booleanuk.app.model.jpa.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ResponseDto {

    @Data
    @AllArgsConstructor
    public static class CustomerDto {
        private int id;
        private String name;
        private String email;
        private List<OrderDto> orders;

        public CustomerDto(Customer customer) {
            setId(customer.getId());
            setName(customer.getName());
            setEmail(customer.getEmail());
            setOrders(Optional.ofNullable(customer.getOrders())
                    .orElseGet(ArrayList::new)
                    .stream()
                    .map(OrderDto::new)
                    .toList());
        }
    }

    @Data
    @AllArgsConstructor
    public static class OrderDto {
        private int id;
        private LocalDate createdAt;
        private double totalAmount;
        private String customerName;

        private List<ProductDto> products;

        public OrderDto(Order order) {
            setId(order.getId());
            setCreatedAt(order.getCreatedAt());
            setTotalAmount(order.getTotalAmount());
            setCustomerName(order.getCustomer().getName());
            setProducts(Optional.ofNullable(order.getProducts())
                    .orElseGet(ArrayList::new)
                    .stream()
                    .map(ProductDto::new)
                    .toList());
        }
    }


    @Data
    @AllArgsConstructor
    public static class ProductDto {
        private int id;
        private String name;
        private double price;

        public ProductDto(Product product) {
            setId(product.getId());
            setName(product.getName());
            setPrice(product.getPrice());
        }
    }
}
