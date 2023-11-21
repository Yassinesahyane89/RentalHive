package com.example.lfri3.controller;

import com.example.lfri3.entity.Customer;
import com.example.lfri3.service.customer.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private final CustomerService customerService;
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/save")
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }

    @GetMapping("/list")
    public List<Customer> getAllCustomer() {
        return customerService.fetchCustomerList();
    }

    @GetMapping("/{customerId}")
    public Optional<Customer> getCustomerById(@PathVariable Long customerId) {
        return customerService.getCustomerById(customerId);
    }
    
    @PutMapping("/{customerId}")
    public Customer updateCustomer(@RequestBody Customer customer, @PathVariable Long customerId) {
        return customerService.updateCustomer(customer, customerId);
    }

    @DeleteMapping("/{customerId}")
    public void deleteCustomer(@PathVariable Long customerId) {
        customerService.deleteCustomerById(customerId);
    }
}
