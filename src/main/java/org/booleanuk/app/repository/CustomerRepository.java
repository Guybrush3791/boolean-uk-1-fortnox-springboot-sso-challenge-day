package org.booleanuk.app.repository;

import org.booleanuk.app.model.pojo.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
