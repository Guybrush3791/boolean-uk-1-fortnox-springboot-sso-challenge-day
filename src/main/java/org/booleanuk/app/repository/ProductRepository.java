package org.booleanuk.app.repository;

import org.booleanuk.app.model.pojo.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
