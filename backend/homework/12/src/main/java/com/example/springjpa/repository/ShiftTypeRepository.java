package com.example.springjpa.repository;

import com.example.springjpa.entities.ShiftType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ShiftTypeRepository extends JpaRepository<ShiftType, UUID> {
    // You can add custom query methods if needed
}
