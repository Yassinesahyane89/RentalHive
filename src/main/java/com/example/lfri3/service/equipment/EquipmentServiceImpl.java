package com.example.lfri3.service.equipment;

import com.example.lfri3.entity.Customer;
import com.example.lfri3.entity.Equipment;
import com.example.lfri3.entity.RentalInfo;
import com.example.lfri3.repository.CustomerRepository;
import com.example.lfri3.repository.EquipmentRepository;
import com.example.lfri3.repository.RentaIInfoRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class EquipmentServiceImpl implements EquipmentService{

    private final EquipmentRepository equipmentRepository;

    private final CustomerRepository customerRepository;

    private final RentaIInfoRepository rentalInfoRepository;


    public EquipmentServiceImpl(EquipmentRepository equipmentRepository, CustomerRepository customerRepository, RentaIInfoRepository rentaIInfoRepository) {
        this.equipmentRepository = equipmentRepository;
        this.customerRepository = customerRepository;
        this.rentalInfoRepository = rentaIInfoRepository;
    }
    @Override
    public Equipment saveEquipment(@Valid Equipment equipment) {

        return equipmentRepository.save(equipment);
    }

    @Override
    public Optional<Equipment> getEquipmentById(Long equipmentId) {
        return equipmentRepository.findById(equipmentId);
    }

    @Override
    public List<Equipment> getEquipmentAvailable() {
        return equipmentRepository.findByAvailableIsTrue();
    }

    @Override
    public List<Equipment> getEquipmentByName(String equipmentName) {
        return equipmentRepository.findByNameContains(equipmentName);
    }

    @Override
    public List<Equipment> fetchEquipmentList() {
        return (List<Equipment>) equipmentRepository.findAll();
    }

    @Override
    public Equipment updateEquipment(Equipment equipment, Long equipmentId) {
        Equipment existingEquipment = equipmentRepository.findById(equipmentId)
                .orElseThrow(() -> new IllegalArgumentException("Equipment not found"));

        // Update the existing equipment with the new details
        //quantity of new equipment sock
        int newQuantityStock = equipment.getInitQuantity()-existingEquipment.getInitQuantity();
        existingEquipment.setName(equipment.getName());
        existingEquipment.setInitQuantity(equipment.getInitQuantity());
        existingEquipment.setAvailableQuantity(existingEquipment.getAvailableQuantity()+newQuantityStock);
        existingEquipment.setDailyRentalCost(equipment.getDailyRentalCost());

        return equipmentRepository.save(existingEquipment);
    }

    @Override
    public void deleteEquipmentById(Long equipmentId) {
        equipmentRepository.deleteById(equipmentId);
    }

}
