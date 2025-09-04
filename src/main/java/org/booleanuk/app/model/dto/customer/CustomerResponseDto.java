package org.booleanuk.app.model.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.booleanuk.app.model.dto.order.OrderSummaryDto;
import org.booleanuk.app.model.pojo.Customer;
import org.booleanuk.app.model.pojo.Order;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponseDto {

    private Long id;
    private String name;
    private String email;
    private List<OrderSummaryDto> orderList;

    public CustomerResponseDto(Customer customer) {
        setId(customer.getId());
        setName(customer.getName());
        setEmail(customer.getEmail());
        if (customer.getOrderList()!= null) {
            setOrderList(customer.getOrderList().stream()
                    .map(OrderSummaryDto::new)
                    .toList());
        }
    }
}
