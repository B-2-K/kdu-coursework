package com.example.springjpa.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private UUID shiftTypeId;

    @ManyToOne
    @JoinColumn(name = "shift_type_id")
    private ShiftType shiftType;

    private String name;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private LocalTime timeStart;
    private LocalTime timeEnd;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String timeZone;
    private UUID tenantId;

    @OneToMany(mappedBy = "shift")
    private List<ShiftUser> shiftUsers;

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;
}
