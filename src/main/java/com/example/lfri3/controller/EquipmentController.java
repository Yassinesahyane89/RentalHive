package com.example.lfri3.controller;


import com.example.lfri3.entity.Equipment;
import com.example.lfri3.request.ReservationRequest;
import com.example.lfri3.service.equipment.EquipmentService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {
    private final EquipmentService equipmentService;

    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @PostMapping("/save")
    public ResponseEntity<Object> createEquipment(@Valid @RequestBody Equipment equipment, BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            for (FieldError error : result.getFieldErrors()) {
                errorMessage.append(error.getField()).append(": ").append(error.getDefaultMessage()).append(".\n");
            }
            return ResponseEntity.badRequest().body(errorMessage.toString());
        }

        equipment.setAvailableQuantity(equipment.getInitQuantity());
        Equipment savedEquipment =  equipmentService.saveEquipment(equipment);
        return ResponseEntity.ok(savedEquipment);
    }

    @GetMapping("/list")
    public List<Equipment> getAllEquipment() {
        return equipmentService.fetchEquipmentList();
    }

    @GetMapping("/{equipmentId}")
    public Optional<Equipment> getEquipmentById(@PathVariable Long equipmentId) {
        return equipmentService.getEquipmentById(equipmentId);
    }

    @GetMapping("/search/equipmentAvailable")
    public List<Equipment> getEquipmentAvailable() {
        return equipmentService.getEquipmentAvailable();
    }

    @GetMapping("/search/equipmentByName")
    public List<Equipment> getEquipmentByName(@RequestBody String equipmentName) {
        return equipmentService.getEquipmentByName(equipmentName);
    }

    @PutMapping("/{equipmentId}")
    public Equipment updateEquipment(@RequestBody Equipment equipment, @PathVariable Long equipmentId) {
        return equipmentService.updateEquipment(equipment, equipmentId);
    }

    @DeleteMapping("/{equipmentId}")
    public void deleteEquipment(@PathVariable Long equipmentId) {
        equipmentService.deleteEquipmentById(equipmentId);
    }

    @PostMapping("/reserve")
    public ResponseEntity<String> reserveEquipment(@Valid @RequestBody ReservationRequest reservationRequest, BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            for (FieldError error : result.getFieldErrors()) {
                errorMessage.append(error.getField()).append(": ").append(error.getDefaultMessage()).append(".\n");
            }
            return ResponseEntity.badRequest().body(errorMessage.toString());
        }

        boolean isReserved = equipmentService.reserveEquipment(
                reservationRequest.getEquipmentId(),
                reservationRequest.getCustomerId(),
                reservationRequest.getStartDate(),
                reservationRequest.getEndDate());
        if (isReserved) {
            return ResponseEntity.ok("Equipment reserved successfully");
        } else {
            return ResponseEntity.badRequest().body("Reservation failed. Equipment not available or invalid IDs provided.");
        }
    }
}
