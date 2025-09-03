package org.booleanuk.app.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    // testing usage of @Query

    // GET value of each Customer (sum of order price by Customer)
    @Query("SELECT c.name, SUM(o.total_amount) " +
            "FROM Customer c, Order o " +
            "WHERE o.customer.id = c.id " +
            "GROUP BY c.name")
    List<Object[]> getCustomerTotalValues();

}
