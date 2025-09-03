package org.booleanuk.app.customer;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.booleanuk.app.order.Order;
import org.booleanuk.app.order.OrderDto;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDto {

    @NotBlank(message = "Name cant be empty")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Name must contain only letters")
    private String name;
    @NotBlank(message = "Email cant be empty")
    private String email;

    private List<OrderDto> orderDtoList;

    public CustomerDto(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderValueDto {
        private String name;
        private long total_amount;

    }
}
