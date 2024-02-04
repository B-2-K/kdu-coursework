package com.kdu.smarthome.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {
    @Id
    private String kickstonId;
    private String deviceUsername;
    private String devicePassword;
    private LocalDateTime manufactureDateTime;
    private String manufactureFactoryPlace;
}

