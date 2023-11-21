package com.example.lfri3.service.equipment;

import com.example.lfri3.entity.Customer;
import com.example.lfri3.entity.Equipment;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface EquipmentService {
    Equipment saveEquipment(Equipment equipment);

    Optional<Equipment> getEquipmentById(Long equipmentId);

    List<Equipment> getEquipmentAvailable();

    List<Equipment> getEquipmentByName(String equipmentName);

    List<Equipment> fetchEquipmentList();

    Equipment updateEquipment(Equipment equipment, Long equipmentId);

    void deleteEquipmentById(Long equipmentId);

    boolean reserveEquipment(Long equipmentId, Long customerId, Date startDate, Date endDate);

    void reserveAndSave(Equipment equipment, Customer customer, Date startDate, Date endDate);

}
