package org.booleanuk.app.model.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerValueDto {
    private long id;
    private String name;
    private int count;
    private float totalAmount;

}
