package org.booleanuk.app.model.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.booleanuk.app.model.dto.customer.CustomerSummaryDto;
import org.booleanuk.app.model.dto.product.ProductSummaryDto;
import org.booleanuk.app.model.pojo.Customer;
import org.booleanuk.app.model.pojo.Order;

import java.time.LocalDateTime;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDto {

    private Long id;
    private LocalDateTime createdAt;
    private float totalAmount;
    private CustomerSummaryDto customer;
    private List<ProductSummaryDto> productList;

    public OrderResponseDto(Order order) {
        setId(order.getId());
        setCreatedAt(order.getCreatedAt());
        setTotalAmount(order.getTotalAmount());
        setCustomer(new CustomerSummaryDto(order.getCustomer()));
        setProductList(order.getProductList().stream()
                .map(ProductSummaryDto::new)
                .toList());
    }

}
