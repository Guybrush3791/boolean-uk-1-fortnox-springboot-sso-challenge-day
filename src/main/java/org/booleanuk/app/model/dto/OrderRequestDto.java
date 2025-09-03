package org.booleanuk.app.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class OrderRequestDto {
    private LocalDate createdAt;
    private double totalAmount;
    @JsonProperty("customer_id")
    private int customerId;
}
