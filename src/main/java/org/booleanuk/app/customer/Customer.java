package org.booleanuk.app.customer;

import org.booleanuk.app.order.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "customers")
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String email;

    // Customer places many orders
    @OneToMany(mappedBy = "customer")
    private List<Order> orderList;

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
