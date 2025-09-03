package org.booleanuk.app.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.booleanuk.app.order.OrderDto;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ProductDto {

    @NotBlank(message = "Product name can not be empty")
    private String name;
    @NotBlank(message = "Price can not be empty")
    private long price;

    private int sold;
    private List<OrderDto> orderDtoList;

    public ProductDto(String name, long price) {
        this.name = name;
        this.price = price;
    }

    public ProductDto(String name, int sold) {
        this.name = name;
        this.sold = sold;
    }
}
