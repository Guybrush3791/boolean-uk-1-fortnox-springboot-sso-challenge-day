package org.booleanuk.app.model.dto.statistics;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.booleanuk.app.model.dto.ResponseDto;

@Data
@AllArgsConstructor
public class ProductCountDto {
    private ResponseDto.ProductDto product;
    private int count;
}
