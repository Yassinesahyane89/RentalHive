package com.example.lfri3.service.customer;

import com.example.lfri3.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Customer saveCustomer(Customer customer);

    Optional<Customer> getCustomerById(Long customerId);

    List<Customer> fetchCustomerList();

    Customer updateCustomer(Customer customer, Long customerId);

    void deleteCustomerById(Long customerId);
}
