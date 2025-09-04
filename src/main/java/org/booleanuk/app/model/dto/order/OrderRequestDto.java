package org.booleanuk.app.model.dto.order;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.booleanuk.app.model.pojo.Customer;
import org.booleanuk.app.model.pojo.Product;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {
    private long customer_id;
    private List<Long> productList;
}
