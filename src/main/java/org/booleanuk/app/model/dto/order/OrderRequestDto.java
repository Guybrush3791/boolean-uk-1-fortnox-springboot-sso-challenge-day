package org.booleanuk.app.model.dto.order;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.booleanuk.app.model.pojo.Customer;
import org.booleanuk.app.model.pojo.Product;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {
    private LocalDateTime createdAt;
    private float totalAmount;
    private Customer customer;
    private List<Product> productList;
}
