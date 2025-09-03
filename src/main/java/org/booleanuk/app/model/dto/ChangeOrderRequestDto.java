package org.booleanuk.app.model.dto;

import java.util.List;

public record ChangeOrderRequestDto(
        List<Integer> productIdsToAdd,
        List<Integer> productIdsToRemove
) {}
