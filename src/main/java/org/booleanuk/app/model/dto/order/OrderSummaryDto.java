package org.booleanuk.app.model.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.booleanuk.app.model.dto.customer.CustomerSummaryDto;
import org.booleanuk.app.model.dto.product.ProductSummaryDto;
import org.booleanuk.app.model.pojo.Customer;
import org.booleanuk.app.model.pojo.Order;
import org.booleanuk.app.model.pojo.Product;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderSummaryDto {
    private long id;
    private LocalDateTime createdAt;
    private float totalAmount;
    private CustomerSummaryDto customer;


    public OrderSummaryDto(Order order) {
        setId(order.getId());
       setCreatedAt(order.getCreatedAt());
       setTotalAmount(order.getTotalAmount());
        setCustomer(new CustomerSummaryDto(order.getCustomer()));
    }
}
