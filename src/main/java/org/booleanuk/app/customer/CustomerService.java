package org.booleanuk.app.customer;

import org.booleanuk.app.order.Order;
import org.booleanuk.app.order.OrderDto;
import org.booleanuk.app.order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.spi.ServiceRegistry;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepo;
    @Autowired
    private OrderRepository orderRepo;

    public Customer createCustomer(CustomerDto dto) {
        Customer customer = new Customer(dto.getName(), dto.getEmail());
        return customerRepo.save(customer);
    }

    public CustomerDto getCustomer(int id) {
        Optional<Customer> customer = customerRepo.findById(id);

        if (customer.isPresent()) {
            List<Order> orderList = customer.get().getOrderList();
            List<OrderDto> orderDtoList = orderList.stream().map(order -> new OrderDto(
                    order.getCreated_at(),
                    order.getTotal_amount()
            )).toList();

            return new CustomerDto(
                    customer.get().getName(),
                    customer.get().getEmail(),
                    orderDtoList
            );
        } else
            throw new NoSuchElementException("Could not find customer");
    }

    public CustomerDto updateCustomer(int id, CustomerDto body) {
        Optional<Customer> customer = customerRepo.findById(id);

        if (customer.isPresent()) {
            customer.get().setName(body.getName());
            customer.get().setEmail(body.getEmail());
            customerRepo.save(customer.get());
            return body;

        } else
            throw new NoSuchElementException("Could not find customer");
    }

    public CustomerDto deleteCustomer(int id) {
        Optional<Customer> customer = customerRepo.findById(id);

        if (customer.isPresent()) {
            CustomerDto dto = new CustomerDto( customer.get().getName(), customer.get().getEmail());
            customerRepo.delete(customer.get());
            return dto;
        } else
            throw new NoSuchElementException("Could not find customer");
    }

    public List<CustomerDto.OrderValueDto> getSumOfCustomerOrders() {
        List<Object[]> list = customerRepo.getCustomerTotalValues();
        List<CustomerDto.OrderValueDto> result = new ArrayList<>();

        for (Object[] objects : list) {
            String name = (String) objects[0];
            long total = ((Number) objects[1]).longValue();
            result.add(new CustomerDto.OrderValueDto(name, total));
        }

        return result;
    }

}
