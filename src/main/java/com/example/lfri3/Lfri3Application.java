package com.example.lfri3;

import com.example.lfri3.entity.Customer;
import com.example.lfri3.entity.Equipment;
import com.example.lfri3.repository.CustomerRepository;
import com.example.lfri3.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Lfri3Application implements CommandLineRunner {

    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private CustomerRepository customerRepository;
    public static void main(String[] args) {
        SpringApplication.run(Lfri3Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        equipmentRepository.save(new Equipment(null, "yassine", "", 122,122, false, 12.33));
        equipmentRepository.save(new Equipment(null, "ss", "", 133,122, true, 1122.3));
        equipmentRepository.save(new Equipment(null, "ggg", "", 155,12, true, 123.3));

        customerRepository.save(new Customer(null,"yassine"));
        customerRepository.save(new Customer(null,"hhh"));
        customerRepository.save(new Customer(null,"yallllssine"));


    }
}
