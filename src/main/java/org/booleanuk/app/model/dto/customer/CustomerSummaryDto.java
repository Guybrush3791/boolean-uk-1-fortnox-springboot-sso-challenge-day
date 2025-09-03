package org.booleanuk.app.model.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.booleanuk.app.model.pojo.Customer;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSummaryDto {

    private Long id;
    private String name;
    private String email;

    public CustomerSummaryDto(Customer customer) {
        setId(customer.getId());
        setName(customer.getName());
        setEmail(customer.getEmail());
    }
}
