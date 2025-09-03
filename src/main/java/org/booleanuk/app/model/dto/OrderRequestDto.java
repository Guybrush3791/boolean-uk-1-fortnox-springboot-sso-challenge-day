package org.booleanuk.app.model.dto;

import java.util.List;

public record OrderRequestDto(
        int customerId,
        List<Integer> productIds
) {}
