package com.kdu.smarthome.repository;

import com.kdu.smarthome.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DeviceRepository extends JpaRepository<Device,Long> {
    @Query
    Optional<Device> findByKickstonId(String kickstonId);
}
