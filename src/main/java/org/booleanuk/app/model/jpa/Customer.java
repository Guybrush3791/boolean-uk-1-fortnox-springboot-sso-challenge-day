package org.booleanuk.app.model.jpa;

import java.util.List;

import org.booleanuk.app.model.dto.CustomerRequestDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String email;

    // Relations
    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
    private List<Order> orders;

    public Customer(String name, String email) {
        setName(name);
        setEmail(email);
    }

    public Customer(CustomerRequestDto customerRequestDto) {
        setName(customerRequestDto.getName());
        setEmail(customerRequestDto.getEmail());
    }

}
