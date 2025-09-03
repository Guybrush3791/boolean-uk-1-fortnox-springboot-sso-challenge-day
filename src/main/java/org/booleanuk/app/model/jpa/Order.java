package org.booleanuk.app.model.jpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.booleanuk.app.model.dto.OrderRequestDto;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate createdAt;
    private double totalAmount;

    // Relations
    @ManyToOne
    @JoinColumn(nullable = false)
    private Customer customer;

    @ManyToMany
    @JoinTable(
        name = "order_products",
        joinColumns = @JoinColumn(name = "order_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;


    // Constructors
    public Order(LocalDate createdAt, double totalAmount, Customer customer, List<Product> products) {
        setCreatedAt(createdAt);
        setTotalAmount(totalAmount);
        setCustomer(customer);
        setProducts(products);
    }

    public Order(OrderRequestDto orderRequestDto) {
        setCreatedAt(orderRequestDto.getCreatedAt());
        setTotalAmount(orderRequestDto.getTotalAmount());
    }
}
