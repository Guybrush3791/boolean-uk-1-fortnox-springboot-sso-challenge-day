package org.booleanuk.app.model.dto.statistics;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.booleanuk.app.model.dto.ResponseDto;
import org.booleanuk.app.model.jpa.Order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
public class OrderTotalValueDto {
    private List<ResponseDto.OrderDto> orders;
    private double totalValue;
}
