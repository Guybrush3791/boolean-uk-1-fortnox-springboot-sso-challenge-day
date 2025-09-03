package org.booleanuk.app.model.dto.report;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderValueResponseDto(
        int orderId,
        LocalDateTime createdAt,
        BigDecimal totalAmount
) {}
