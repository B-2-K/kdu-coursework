package com.kdu.smarthome.repository;

import com.kdu.smarthome.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepository extends JpaRepository<House, Long> {
}
