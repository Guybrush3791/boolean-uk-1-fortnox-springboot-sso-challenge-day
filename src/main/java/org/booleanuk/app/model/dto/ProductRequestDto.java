package org.booleanuk.app.model.dto;

import java.math.BigDecimal;

public record ProductRequestDto(
        String name,
        BigDecimal price
) {}