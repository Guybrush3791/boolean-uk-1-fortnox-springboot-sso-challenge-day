package org.booleanuk.app.model.jpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.booleanuk.app.model.dto.ProductRequestDto;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private double price;

    public Product(String name, double price) {
        setName(name);
        setPrice(price);
    }

    public Product(ProductRequestDto productRequestDto) {
        setName(productRequestDto.getName());
        setPrice(productRequestDto.getPrice());
    }
}
