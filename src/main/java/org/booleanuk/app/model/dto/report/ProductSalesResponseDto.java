package org.booleanuk.app.model.dto.report;

public record ProductSalesResponseDto(
        int productId,
        String productName,
        long unitsSold
) {}