package org.booleanuk.app.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.booleanuk.app.order.Order;

import java.util.List;

@Data
@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private long price;

    // Many to many order - products
    @ManyToMany(mappedBy = "productList", cascade = CascadeType.REMOVE)
    private List<Order> orderList;

    public Product(String name, long price) {
        this.name = name;
        this.price = price;
    }
}
