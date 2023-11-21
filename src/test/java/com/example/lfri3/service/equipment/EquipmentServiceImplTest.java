package com.example.lfri3.service.equipment;

import com.example.lfri3.entity.Equipment;
import com.example.lfri3.repository.CustomerRepository;
import com.example.lfri3.repository.EquipmentRepository;
import com.example.lfri3.repository.RentaIInfoRepository;
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

import static org.junit.jupiter.api.Assertions.*;

class EquipmentServiceImplTest {

        @InjectMocks
        private EquipmentServiceImpl equipmentService;

        @Mock
        private EquipmentRepository equipmentRepository;

        @BeforeEach
        void setUp(){
            MockitoAnnotations.openMocks(this);
        }
        // Can save equipment successfully
        @Test
        public void test_saveEquipment_successfully() {
            EquipmentRepository equipmentRepository = Mockito.mock(EquipmentRepository.class);
            CustomerRepository customerRepository = Mockito.mock(CustomerRepository.class);
            RentaIInfoRepository rentalInfoRepository = Mockito.mock(RentaIInfoRepository.class);
            EquipmentServiceImpl equipmentService = new EquipmentServiceImpl(equipmentRepository, customerRepository, rentalInfoRepository);

            Equipment equipment = new Equipment();
            equipment.setName("Equipment 1");
            equipment.setInitQuantity(10);
            equipment.setAvailableQuantity(10);
            equipment.setDailyRentalCost(10.0);

            Mockito.when(equipmentRepository.save(Mockito.any(Equipment.class))).thenReturn(equipment);

            Equipment savedEquipment = equipmentService.saveEquipment(equipment);

            assertEquals(equipment, savedEquipment);
        }

        // Can get equipment by ID successfully
        @Test
        public void test_getEquipmentById_successfully() {
            EquipmentRepository equipmentRepository = Mockito.mock(EquipmentRepository.class);
            CustomerRepository customerRepository = Mockito.mock(CustomerRepository.class);
            RentaIInfoRepository rentalInfoRepository = Mockito.mock(RentaIInfoRepository.class);
            EquipmentServiceImpl equipmentService = new EquipmentServiceImpl(equipmentRepository, customerRepository, rentalInfoRepository);

            Long equipmentId = 1L;
            Equipment equipment = new Equipment();
            equipment.setId(equipmentId);
            equipment.setName("Equipment 1");
            equipment.setInitQuantity(10);
            equipment.setAvailableQuantity(10);
            equipment.setDailyRentalCost(10.0);

            Mockito.when(equipmentRepository.findById(equipmentId)).thenReturn(Optional.of(equipment));

            Optional<Equipment> retrievedEquipment = equipmentService.getEquipmentById(equipmentId);

            assertTrue(retrievedEquipment.isPresent());
            assertEquals(equipment, retrievedEquipment.get());
        }

        // Can get available equipment successfully
        @Test
        public void test_getEquipmentAvailable_successfully() {
            EquipmentRepository equipmentRepository = Mockito.mock(EquipmentRepository.class);
            CustomerRepository customerRepository = Mockito.mock(CustomerRepository.class);
            RentaIInfoRepository rentalInfoRepository = Mockito.mock(RentaIInfoRepository.class);
            EquipmentServiceImpl equipmentService = new EquipmentServiceImpl(equipmentRepository, customerRepository, rentalInfoRepository);

            List<Equipment> availableEquipment = new ArrayList<>();
            Equipment equipment1 = new Equipment();
            equipment1.setId(1L);
            equipment1.setName("Equipment 1");
            equipment1.setInitQuantity(10);
            equipment1.setAvailableQuantity(5);
            equipment1.setDailyRentalCost(10.0);
            availableEquipment.add(equipment1);

            Equipment equipment2 = new Equipment();
            equipment2.setId(2L);
            equipment2.setName("Equipment 2");
            equipment2.setInitQuantity(5);
            equipment2.setAvailableQuantity(0);
            equipment2.setDailyRentalCost(15.0);
            availableEquipment.add(equipment2);

            Mockito.when(equipmentRepository.findByAvailableIsTrue()).thenReturn(availableEquipment);

            List<Equipment> retrievedEquipment = equipmentService.getEquipmentAvailable();

            assertEquals(availableEquipment, retrievedEquipment);
        }

        // Can get equipment by name successfully
        @Test
        public void test_getEquipmentByName_successfully() {
            EquipmentRepository equipmentRepository = Mockito.mock(EquipmentRepository.class);
            CustomerRepository customerRepository = Mockito.mock(CustomerRepository.class);
            RentaIInfoRepository rentalInfoRepository = Mockito.mock(RentaIInfoRepository.class);
            EquipmentServiceImpl equipmentService = new EquipmentServiceImpl(equipmentRepository, customerRepository, rentalInfoRepository);

            String equipmentName = "Equipment";
            List<Equipment> equipmentList = new ArrayList<>();
            Equipment equipment1 = new Equipment();
            equipment1.setId(1L);
            equipment1.setName("Equipment 1");
            equipment1.setInitQuantity(10);
            equipment1.setAvailableQuantity(10);
            equipment1.setDailyRentalCost(10.0);
            equipmentList.add(equipment1);

            Equipment equipment2 = new Equipment();
            equipment2.setId(2L);
            equipment2.setName("Equipment 2");
            equipment2.setInitQuantity(5);
            equipment2.setAvailableQuantity(5);
            equipment2.setDailyRentalCost(15.0);
            equipmentList.add(equipment2);

            Mockito.when(equipmentRepository.findByNameContains(equipmentName)).thenReturn(equipmentList);

            List<Equipment> retrievedEquipment = equipmentService.getEquipmentByName(equipmentName);

            assertEquals(equipmentList, retrievedEquipment);
        }

        // Can fetch list of equipment successfully
        @Test
        public void test_fetchEquipmentList_successfully() {
            EquipmentRepository equipmentRepository = Mockito.mock(EquipmentRepository.class);
            CustomerRepository customerRepository = Mockito.mock(CustomerRepository.class);
            RentaIInfoRepository rentalInfoRepository = Mockito.mock(RentaIInfoRepository.class);
            EquipmentServiceImpl equipmentService = new EquipmentServiceImpl(equipmentRepository, customerRepository, rentalInfoRepository);

            List<Equipment> equipmentList = new ArrayList<>();
            Equipment equipment1 = new Equipment();
            equipment1.setId(1L);
            equipment1.setName("Equipment 1");
            equipment1.setInitQuantity(10);
            equipment1.setAvailableQuantity(10);
            equipment1.setDailyRentalCost(10.0);
            equipmentList.add(equipment1);

            Equipment equipment2 = new Equipment();
            equipment2.setId(2L);
            equipment2.setName("Equipment 2");
            equipment2.setInitQuantity(5);
            equipment2.setAvailableQuantity(5);
            equipment2.setDailyRentalCost(15.0);
            equipmentList.add(equipment2);

            Mockito.when(equipmentRepository.findAll()).thenReturn(equipmentList);

            List<Equipment> retrievedEquipment = equipmentService.fetchEquipmentList();

            assertEquals(equipmentList, retrievedEquipment);
        }

        // Can update equipment successfully
        @Test
        public void test_updateEquipment_successfully() {
            EquipmentRepository equipmentRepository = Mockito.mock(EquipmentRepository.class);
            CustomerRepository customerRepository = Mockito.mock(CustomerRepository.class);
            RentaIInfoRepository rentalInfoRepository = Mockito.mock(RentaIInfoRepository.class);
            EquipmentServiceImpl equipmentService = new EquipmentServiceImpl(equipmentRepository, customerRepository, rentalInfoRepository);

            Long equipmentId = 1L;
            Equipment existingEquipment = new Equipment();
            existingEquipment.setId(equipmentId);
            existingEquipment.setName("Existing Equipment");
            existingEquipment.setInitQuantity(10);
            existingEquipment.setAvailableQuantity(10);
            existingEquipment.setDailyRentalCost(10.0);

            Equipment updatedEquipment = new Equipment();
            updatedEquipment.setId(equipmentId);
            updatedEquipment.setName("Updated Equipment");
            updatedEquipment.setInitQuantity(15);
            updatedEquipment.setAvailableQuantity(15);
            updatedEquipment.setDailyRentalCost(12.0);

            Mockito.when(equipmentRepository.findById(equipmentId)).thenReturn(Optional.of(existingEquipment));
            Mockito.when(equipmentRepository.save(Mockito.any(Equipment.class))).thenReturn(updatedEquipment);

            Equipment result = equipmentService.updateEquipment(updatedEquipment, equipmentId);

            assertEquals(updatedEquipment, result);
        }

        // Cannot get equipment by invalid ID
        @Test
        public void test_getEquipmentByInvalidId() {
            EquipmentRepository equipmentRepository = Mockito.mock(EquipmentRepository.class);
            CustomerRepository customerRepository = Mockito.mock(CustomerRepository.class);
            RentaIInfoRepository rentalInfoRepository = Mockito.mock(RentaIInfoRepository.class);
            EquipmentServiceImpl equipmentService = new EquipmentServiceImpl(equipmentRepository, customerRepository, rentalInfoRepository);

            Long invalidId = 100L;

            Mockito.when(equipmentRepository.findById(invalidId)).thenReturn(Optional.empty());

            Optional<Equipment> retrievedEquipment = equipmentService.getEquipmentById(invalidId);

            assertFalse(retrievedEquipment.isPresent());
        }
}