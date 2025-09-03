package org.booleanuk.app.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.booleanuk.app.customer.CustomerDto;
import org.booleanuk.app.product.ProductDto;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDto {

    @NotBlank(message = "Created at can not be empty")
    private String created_at;
    private long total_amount;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int customer_id;
    private CustomerDto customer;
    private List<ProductDto> productList;

    public OrderDto(String created_at, long total_amount, CustomerDto customer, List<ProductDto> productList) {
        this.created_at = created_at;
        this.total_amount = total_amount;
        this.customer = customer;
        this.productList = productList;
    }

    public OrderDto(String created_at, long total_amount) {
        this.created_at = created_at;
        this.total_amount = total_amount;
    }

}
