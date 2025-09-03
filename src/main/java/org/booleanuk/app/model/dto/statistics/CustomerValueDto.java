package org.booleanuk.app.model.dto.statistics;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.booleanuk.app.model.dto.ResponseDto;
import org.booleanuk.app.model.jpa.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
public class CustomerValueDto {
    private int id;
    private String name;
    private String email;
    private List<ResponseDto.OrderDto> orders;
    private double totalPrice;

    public CustomerValueDto(Customer customer, double totalPrice) {
        setId(customer.getId());
        setName(customer.getName());
        setEmail(customer.getEmail());
        setOrders(Optional.ofNullable(customer.getOrders())
                .orElseGet(ArrayList::new)
                .stream()
                .map(ResponseDto.OrderDto::new)
                .toList());
        setTotalPrice(totalPrice);
    }
}
