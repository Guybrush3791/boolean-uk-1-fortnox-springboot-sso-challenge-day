package org.booleanuk.app.model.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.booleanuk.app.model.pojo.Order;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {
    private String name;
    private float price;
    private List<Order> orderList;
}
