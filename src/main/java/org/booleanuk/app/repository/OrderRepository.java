package org.booleanuk.app.repository;

import org.booleanuk.app.model.pojo.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {


}
