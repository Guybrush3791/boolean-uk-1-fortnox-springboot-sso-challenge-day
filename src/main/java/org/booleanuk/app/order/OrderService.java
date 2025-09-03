package org.booleanuk.app.order;

import org.booleanuk.app.customer.Customer;
import org.booleanuk.app.customer.CustomerDto;
import org.booleanuk.app.customer.CustomerRepository;
import org.booleanuk.app.product.Product;
import org.booleanuk.app.product.ProductDto;
import org.booleanuk.app.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.*;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepo;
    @Autowired
    private CustomerRepository customerRepo;
    @Autowired
    private ProductRepository productRepo;


    public OrderDto createOrder(OrderDto dto) {
        Optional<Customer> customerOpt = customerRepo.findById(dto.getCustomer_id());
        if (customerOpt.isEmpty()) {
            throw new NoSuchElementException("Customer not found.");
        }

        List<Product> productList = new ArrayList<>();
        for (ProductDto productDto : dto.getProductList()) {
            Optional<Product> productOpt = productRepo.findByName(productDto.getName());
            if (productOpt.isEmpty()) {
                throw new NoSuchElementException("Product not found: " + productDto.getName());
            }
            productList.add(productOpt.get());
        }
        Order order = new Order(
                dto.getCreated_at(),
                dto.getTotal_amount(),
                customerOpt.get(),
                productList
        );

        orderRepo.save(order);
        return convertToDto(order);
    }

    //    GET REQUEST   //
    public OrderDto getOrder(int id) {
        Optional<Order> orderOpt = orderRepo.findById(id);

        if (orderOpt.isPresent()) {
            Order order = orderOpt.get();
            return convertToDto(order);

        } else {
            throw new NoSuchElementException("Order not found.");
        }
    }

    //    PUT REQUEST FROM PUBLIC   //
    public OrderDto updateOrderPublic(int id, OrderDto dto) {
        Optional<Order> orderOpt = orderRepo.findById(id);

        if (orderOpt.isPresent()) {
            List<Product> productList = new ArrayList<>();

            for (ProductDto productDto : dto.getProductList()) {
                Optional<Product> productOpt = productRepo.findByName(productDto.getName());
                if (productOpt.isEmpty()) {
                    throw new NoSuchElementException("Product not found: " + productDto.getName());
                }
                productList.add(productOpt.get());
            }

            orderOpt.get().setProductList(productList);
            orderRepo.save(orderOpt.get());

            return convertToDto(orderOpt.get());
        } else
            throw new NoSuchElementException("Could not find the order to update.");
    }

    //    PUT REQUEST FROM PRIVATE - ENABLE MORE SETTERS   //
    public OrderDto updateOrderPrivate(int id, OrderDto dto) {
        Optional<Order> orderOpt = orderRepo.findById(id);

        if (orderOpt.isPresent()) {
            List<Product> productList = new ArrayList<>();

            for (ProductDto productDto : dto.getProductList()) {
                Optional<Product> productOpt = productRepo.findByName(productDto.getName());
                if (productOpt.isEmpty()) {
                    throw new NoSuchElementException("Product not found: " + productDto.getName());
                }
                productList.add(productOpt.get());
            }

            orderOpt.get().setProductList(productList);
            orderOpt.get().setCreated_at(dto.getCreated_at());
            orderOpt.get().setTotal_amount(dto.getTotal_amount());

            orderRepo.save(orderOpt.get());
            return convertToDto(orderOpt.get());

        } else
            throw new NoSuchElementException("Could not find the order to update.");
    }

    public OrderDto deleteOrder(int id) {
        Optional<Order> order = orderRepo.findById(id);

        if (order.isPresent()) {
            OrderDto dto = new OrderDto(
                    order.get().getCreated_at(),
                    order.get().getTotal_amount()
            );
            orderRepo.delete(order.get());
            return dto;
        } else
            throw new NoSuchElementException("Could not find order");
    }

    public List<OrderDto> getOrdersTotalSorted() {
        List<OrderDto> orderDtos = new ArrayList<>(orderRepo.findAll().stream()
                .map(order -> {
                    List<Product> productList = order.getProductList();
                    long totCost = 0;
                    for (Product product : productList) {
                        totCost += product.getPrice();
                    }

                    order.setTotal_amount(totCost);

                    return new OrderDto(order.getCreated_at(), order.getTotal_amount());
                }).toList());

        orderDtos.sort(Comparator.comparing(OrderDto::getTotal_amount).reversed());
        return orderDtos;
    }


    //    HELPER CLASS   //
    public OrderDto convertToDto(Order order) {
        CustomerDto customerDto = new CustomerDto(
                order.getCustomer().getName(),
                order.getCustomer().getEmail()
        );

        List<ProductDto> productDtoList = order.getProductList().stream()
                .map(product -> new ProductDto(product.getName(), product.getPrice()))
                .toList();

        return new OrderDto(
                order.getCreated_at(),
                order.getTotal_amount(),
                customerDto,
                productDtoList
        );
    }

}
