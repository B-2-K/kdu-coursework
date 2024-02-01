package com.example.springjpa.repository;

import com.example.springjpa.entities.Shift;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ShiftRepository extends JpaRepository<Shift, UUID> {

    @Query("SELECT s FROM Shift s " +
            "WHERE s.dateStart = :startDate AND s.dateEnd = :endDate " +
            "ORDER BY s.name ASC")
    List<Shift> findTop3ShiftsByDateRange(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            Pageable pageable);
}
