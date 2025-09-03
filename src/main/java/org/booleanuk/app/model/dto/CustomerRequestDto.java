package org.booleanuk.app.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomerRequestDto {
    private String name;
    private String email;
}
