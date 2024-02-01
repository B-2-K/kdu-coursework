package com.example.springjpa.controllers;

import com.example.springjpa.entities.ShiftType;
import com.example.springjpa.services.ShiftTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/shift-types")
public class ShiftTypeController {

    @Autowired
    private ShiftTypeService shiftTypeService;

    @GetMapping
    public List<ShiftType> getAllShiftTypes() {
        return shiftTypeService.getAllShiftTypes();
    }

    @GetMapping("/{id}")
    public Optional<ShiftType> getShiftTypeById(@PathVariable UUID id) {
        return shiftTypeService.getShiftTypeById(id);
    }

    @PostMapping
    public ShiftType saveShiftType(@RequestBody ShiftType shiftType) {
        return shiftTypeService.saveShiftType(shiftType);
    }

    @DeleteMapping("/{id}")
    public void deleteShiftType(@PathVariable UUID id) {
        shiftTypeService.deleteShiftType(id);
    }
}
