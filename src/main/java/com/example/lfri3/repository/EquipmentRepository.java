package com.example.lfri3.repository;

import com.example.lfri3.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;

@RepositoryRestResource
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {//interface generique

    @RestResource(path = "/equipmentsAvailable")
    public List<Equipment> findByAvailableIsTrue();

    @RestResource(path = "/equipmentsByName")
    public List<Equipment> findByNameContains(@Param("name") String name);

}
