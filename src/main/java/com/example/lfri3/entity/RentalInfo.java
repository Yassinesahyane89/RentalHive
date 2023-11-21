package com.example.lfri3.entity;

import com.example.lfri3.validation.EndDateAfterStartDate;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RentalInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Equipment equipment;

    @ManyToOne
    private Customer customer;

    @NotNull(message = "Start date cannot be null")
    @FutureOrPresent(message = "Start date must be in the present or future")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @NotNull(message = "End date cannot be null")
    @FutureOrPresent(message = "End date must be in the present or future")
    @Temporal(TemporalType.DATE)
    private Date endDate;
}
