package org.booleanuk.app.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponseDto(
        int id,
        LocalDateTime createdAt,
        BigDecimal totalAmount,
        CustomerResponseDto customer,
        List<ProductResponseDto> products
) {}
