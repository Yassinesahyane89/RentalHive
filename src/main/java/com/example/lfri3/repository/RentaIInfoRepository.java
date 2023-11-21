package com.example.lfri3.repository;

import com.example.lfri3.entity.RentalInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface RentaIInfoRepository extends JpaRepository<RentalInfo, Long> {
    @Query("SELECT r FROM RentalInfo r " +
            "WHERE r.equipment.id = :equipmentId " +
            "AND ((r.startDate BETWEEN :startDate AND :endDate) OR " +
            "(r.endDate BETWEEN :startDate AND :endDate))")
    List<RentalInfo> findConflictingReservations(
            @Param("equipmentId") Long equipmentId,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate
    );
}
