package org.booleanuk.app.model.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.booleanuk.app.model.pojo.Product;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSalesStatisticsDto {
    private long id;
    private String name;
    private int count;

    public ProductSalesStatisticsDto(Product product) {
        setId(product.getId());
        setName(product.getName());
        setCount(product.getOrderList().size());
    }
}
