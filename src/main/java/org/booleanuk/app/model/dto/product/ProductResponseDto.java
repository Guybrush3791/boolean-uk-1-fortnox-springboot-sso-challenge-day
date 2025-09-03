package org.booleanuk.app.model.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.booleanuk.app.model.dto.order.OrderSummaryDto;
import org.booleanuk.app.model.pojo.Order;
import org.booleanuk.app.model.pojo.Product;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {
    private long id;
    private String name;
    private float price;
    private List<OrderSummaryDto> orderList;

    public ProductResponseDto(Product product) {
        setId(product.getId());
        setName(product.getName());
        setPrice(product.getPrice());
       setOrderList(product.getOrderList().stream()
                .map(OrderSummaryDto::new)
               .toList());
    }
}

