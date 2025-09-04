package org.booleanuk.app.model.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.booleanuk.app.model.pojo.Order;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequestDto {
    private String name;
    private String email;
    private List<Order> orderList = null;
}
