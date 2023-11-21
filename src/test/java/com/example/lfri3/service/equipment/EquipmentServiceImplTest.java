package com.example.lfri3.service.equipment;

import com.example.lfri3.entity.Equipment;
import com.example.lfri3.repository.CustomerRepository;
import com.example.lfri3.repository.EquipmentRepository;
import com.example.lfri3.repository.RentaIInfoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class EquipmentServiceImplTest {

    // can update an equipment
    private EquipmentServiceImpl equipmentService;
    @Test
    public void test_updateEquipment() {
        EquipmentRepository equipmentRepository = Mockito.mock(EquipmentRepository.class);
        CustomerRepository customerRepository = Mockito.mock(CustomerRepository.class);
        RentaIInfoRepository rentalInfoRepository = Mockito.mock(RentaIInfoRepository.class);
        EquipmentServiceImpl equipmentService = new EquipmentServiceImpl(equipmentRepository, customerRepository, rentalInfoRepository);

        Long equipmentId = 1L;
        Equipment existingEquipment = new Equipment(1L, "Equipment 1", "image1.jpg", 10, 10, true, 10.0);
        Equipment updatedEquipment = new Equipment(1L, "Updated Equipment", "image1.jpg", 15, 15, true, 12.0);

        Mockito.when(equipmentRepository.findById(eq(equipmentId))).thenReturn(Optional.of(existingEquipment));
        Mockito.when(equipmentRepository.save(any(Equipment.class))).thenReturn(updatedEquipment);

        Equipment actualEquipment = equipmentService.updateEquipment(updatedEquipment, equipmentId);

        assertEquals(updatedEquipment, actualEquipment);
    }

    // can save an equipment
    @Test
    public void test_saveEquipment() {
        Equipment equipment = Equipment.builder()
                .name("Equipment 1")
                .image("image.jpg")
                .initQuantity(10)
                .availableQuantity(10)
                .available(true)
                .dailyRentalCost(10.0)
                .build();

        Equipment savedEquipment = equipmentService.saveEquipment(equipment);

        assertNotNull(savedEquipment);
        assertEquals(equipment.getName(), savedEquipment.getName());
        assertEquals(equipment.getImage(), savedEquipment.getImage());
        assertEquals(equipment.getInitQuantity(), savedEquipment.getInitQuantity());
        assertEquals(equipment.getAvailableQuantity(), savedEquipment.getAvailableQuantity());
        assertEquals(equipment.getAvailable(), savedEquipment.getAvailable());
        assertEquals(equipment.getDailyRentalCost(), savedEquipment.getDailyRentalCost());
    }

    // can get an equipment by id
    @Test
    public void test_getEquipmentById() {
        Equipment equipment = Equipment.builder()
                .name("Equipment 1")
                .image("image.jpg")
                .initQuantity(10)
                .availableQuantity(10)
                .available(true)
                .dailyRentalCost(10.0)
                .build();

        Equipment savedEquipment = equipmentService.saveEquipment(equipment);

        Optional<Equipment> retrievedEquipment = equipmentService.getEquipmentById(savedEquipment.getId());

        assertTrue(retrievedEquipment.isPresent());
        assertEquals(savedEquipment, retrievedEquipment.get());
    }

    // can get available equipments
    @Test
    public void test_getEquipmentAvailable() {
        Equipment equipment1 = Equipment.builder()
                .name("Equipment 1")
                .image("image.jpg")
                .initQuantity(10)
                .availableQuantity(10)
                .available(true)
                .dailyRentalCost(10.0)
                .build();

        Equipment equipment2 = Equipment.builder()
                .name("Equipment 2")
                .image("image.jpg")
                .initQuantity(5)
                .availableQuantity(0)
                .available(false)
                .dailyRentalCost(15.0)
                .build();

        equipmentService.saveEquipment(equipment1);
        equipmentService.saveEquipment(equipment2);

        List<Equipment> availableEquipments = equipmentService.getEquipmentAvailable();

        assertEquals(1, availableEquipments.size());
        assertEquals(equipment1, availableEquipments.get(0));
    }

    // can get equipments by name
    @Test
    public void test_getEquipmentByName() {
        Equipment equipment1 = Equipment.builder()
                .name("Equipment 1")
                .image("image.jpg")
                .initQuantity(10)
                .availableQuantity(10)
                .available(true)
                .dailyRentalCost(10.0)
                .build();

        Equipment equipment2 = Equipment.builder()
                .name("Equipment 2")
                .image("image.jpg")
                .initQuantity(5)
                .availableQuantity(5)
                .available(true)
                .dailyRentalCost(15.0)
                .build();

        equipmentService.saveEquipment(equipment1);
        equipmentService.saveEquipment(equipment2);

        List<Equipment> equipments = equipmentService.getEquipmentByName("Equipment");

        assertEquals(2, equipments.size());
        assertTrue(equipments.contains(equipment1));
        assertTrue(equipments.contains(equipment2));
    }

    // can fetch all equipments
    @Test
    public void test_fetchEquipmentList() {
        Equipment equipment1 = Equipment.builder()
                .name("Equipment 1")
                .image("image.jpg")
                .initQuantity(10)
                .availableQuantity(10)
                .available(true)
                .dailyRentalCost(10.0)
                .build();

        Equipment equipment2 = Equipment.builder()
                .name("Equipment 2")
                .image("image.jpg")
                .initQuantity(5)
                .availableQuantity(5)
                .available(true)
                .dailyRentalCost(15.0)
                .build();

        equipmentService.saveEquipment(equipment1);
        equipmentService.saveEquipment(equipment2);

        List<Equipment> equipments = equipmentService.fetchEquipmentList();

        assertEquals(2, equipments.size());
        assertTrue(equipments.contains(equipment1));
        assertTrue(equipments.contains(equipment2));
    }

    // can update an equipment
    @Test
    public void test_updateEquipment() {
        Equipment equipment = Equipment.builder()
                .name("Equipment 1")
                .image("image.jpg")
                .initQuantity(10)
                .availableQuantity(10)
                .available(true)
                .dailyRentalCost(10.0)
                .build();

        Equipment savedEquipment = equipmentService.saveEquipment(equipment);

        Equipment updatedEquipment = Equipment.builder()
                .name("Updated Equipment")
                .image("updated_image.jpg")
                .initQuantity(15)
                .availableQuantity(15)
                .available(true)
                .dailyRentalCost(12.0)
                .build();

        Equipment result = equipmentService.updateEquipment(updatedEquipment, savedEquipment.getId());

        assertNotNull(result);
        assertEquals(updatedEquipment.getName(), result.getName());
        assertEquals(updatedEquipment.getImage(), result.getImage());
        assertEquals(updatedEquipment.getInitQuantity(), result.getInitQuantity());
        assertEquals(updatedEquipment.getAvailableQuantity(), result.getAvailableQuantity());
        assertEquals(updatedEquipment.getAvailable(), result.getAvailable());
        assertEquals(updatedEquipment.getDailyRentalCost(), result.getDailyRentalCost());
    }

    // throws exception when updating non-existing equipment
    @Test
    public void test_updateNonExistingEquipment() {
        Equipment equipment = Equipment.builder()
                .name("Equipment 1")
                .image("image.jpg")
                .initQuantity(10)
                .availableQuantity(10)
                .available(true)
                .dailyRentalCost(10.0)
                .build();

        assertThrows(IllegalArgumentException.class, () -> {
            equipmentService.updateEquipment(equipment, 100L);
        });
    }

    // does not reserve an equipment if it is not available and there are conflicting reservations
    @Test
    public void test_reserveEquipmentNotAvailableWithConflictingReservations() {
        Equipment equipment = Equipment.builder()
                .name("Equipment 1")
                .image("image.jpg")
                .initQuantity(10)
                .availableQuantity(0)
                .available(false)
                .dailyRentalCost(10.0)
                .build();

        Customer customer = Customer.builder()
                .name("Customer 1")
                .build();

        Date startDate = new Date();
        Date endDate = new Date();

        boolean result = equipmentService.reserveEquipment(equipment.getId(), customer.getId(), startDate, endDate);

        assertFalse(result);
    }

    // does not reserve an equipment if either the equipment or customer is null
    @Test
    public void test_reserveEquipmentWithNullEquipmentOrCustomer() {
        Equipment equipment = Equipment.builder()
                .name("Equipment 1")
                .image("image.jpg")
                .initQuantity(10)
                .availableQuantity(10)
                .available(true)
                .dailyRentalCost(10.0)
                .build();

        Customer customer = Customer.builder()
                .name("Customer 1")
                .build();

        Date startDate = new Date();
        Date endDate = new Date();

        boolean result1 = equipmentService.reserveEquipment(null, customer.getId(), startDate, endDate);
        boolean result2 = equipmentService.reserveEquipment(equipment.getId(), null, startDate, endDate);

        assertFalse(result1);
        assertFalse(result2);
    }
}