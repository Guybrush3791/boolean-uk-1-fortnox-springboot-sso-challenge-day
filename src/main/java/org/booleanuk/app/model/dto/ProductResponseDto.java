package org.booleanuk.app.model.dto;

import java.math.BigDecimal;

public record ProductResponseDto(
        int id,
        String name,
        BigDecimal price
) {}
