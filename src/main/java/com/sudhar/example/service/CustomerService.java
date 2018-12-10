package com.sudhar.example.service;

import com.sudhar.example.domain.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final List<Customer> customerList;

    public CustomerService() {
        customerList = new ArrayList<>();
        customerList.add(new Customer(1, "sudhar", "san"));
        customerList.add(new Customer(2, "santa", "fe"));
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public Customer getById(final int id) {
        final Optional<Customer> filter = customerList.stream().filter(customer -> customer.getId() == id).findFirst();
        return filter.get();
    }
}
