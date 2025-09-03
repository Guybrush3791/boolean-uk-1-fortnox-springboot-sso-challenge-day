package org.booleanuk.app.model.repository;

import org.booleanuk.app.model.jpa.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
