package com.kdu.smarthome.repository;

import com.kdu.smarthome.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long> {

    @Query
    Optional<Inventory> findByKickstonId(String kickstonId);
}
