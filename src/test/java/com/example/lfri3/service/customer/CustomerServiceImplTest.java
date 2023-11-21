package com.example.lfri3.service.customer;

import static org.junit.jupiter.api.Assertions.*;

import com.example.lfri3.entity.Customer;
import com.example.lfri3.entity.Equipment;
import com.example.lfri3.repository.CustomerRepository;
import com.example.lfri3.repository.EquipmentRepository;
import com.example.lfri3.repository.RentaIInfoRepository;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

class CustomerServiceImplTest {
    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void test_saveCustomer_successfully() {
        CustomerRepository customerRepository = Mockito.mock(CustomerRepository.class);
        CustomerServiceImpl customerService = new CustomerServiceImpl(customerRepository);

        Customer customer = new Customer();
        customer.setName("John Doe");

        Mockito.when(customerRepository.save(customer)).thenReturn(customer);

        Customer savedCustomer = customerService.saveCustomer(customer);

        assertEquals(customer, savedCustomer);
    }

    // can retrieve a customer by id successfully
    @Test
    public void test_getCustomerById_successfully() {
        CustomerRepository customerRepository = Mockito.mock(CustomerRepository.class);
        CustomerServiceImpl customerService = new CustomerServiceImpl(customerRepository);

        Long customerId = 1L;
        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setName("John Doe");

        Mockito.when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        Optional<Customer> retrievedCustomer = customerService.getCustomerById(customerId);

        assertTrue(retrievedCustomer.isPresent());
        assertEquals(customer, retrievedCustomer.get());
    }

    // can retrieve a list of customers successfully
    @Test
    public void test_fetchCustomerList_successfully() {
        CustomerRepository customerRepository = Mockito.mock(CustomerRepository.class);
        CustomerServiceImpl customerService = new CustomerServiceImpl(customerRepository);

        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer(1L, "John Doe"));
        customers.add(new Customer(2L, "Jane Smith"));

        Mockito.when(customerRepository.findAll()).thenReturn(customers);

        List<Customer> retrievedCustomers = customerService.fetchCustomerList();

        assertEquals(customers, retrievedCustomers);
    }

    // can delete a customer by id successfully
    @Test
    public void test_deleteCustomerById_successfully() {
        CustomerRepository customerRepository = Mockito.mock(CustomerRepository.class);
        CustomerServiceImpl customerService = new CustomerServiceImpl(customerRepository);

        Long customerId = 1L;

        customerService.deleteCustomerById(customerId);

        Mockito.verify(customerRepository).deleteById(customerId);
    }

    // attempting to retrieve a customer with an invalid id returns an empty optional
    @Test
    public void test_getCustomerById_invalidId_returnsEmptyOptional() {
        CustomerRepository customerRepository = Mockito.mock(CustomerRepository.class);
        CustomerServiceImpl customerService = new CustomerServiceImpl(customerRepository);

        Long invalidId = -1L;

        Mockito.when(customerRepository.findById(invalidId)).thenReturn(Optional.empty());

        Optional<Customer> retrievedCustomer = customerService.getCustomerById(invalidId);

        assertFalse(retrievedCustomer.isPresent());
    }
}