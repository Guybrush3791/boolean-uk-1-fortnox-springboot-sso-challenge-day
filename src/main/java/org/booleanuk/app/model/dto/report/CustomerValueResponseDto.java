package org.booleanuk.app.model.dto.report;

import java.math.BigDecimal;

public record CustomerValueResponseDto(
        int customerId,
        String customerName,
        BigDecimal totalValue
) {}
