package com.example.springjpa.controllers;

import com.example.springjpa.entities.Shift;
import com.example.springjpa.services.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/shifts")
public class ShiftController {

    @Autowired
    private ShiftService shiftService;

    @GetMapping
    public List<Shift> getAllShifts() {
        return shiftService.getAllShifts();
    }

    @GetMapping("/{id}")
    public Optional<Shift> getShiftById(@PathVariable UUID id) {
        return shiftService.getShiftById(id);
    }

    @PostMapping
    public Shift saveShift(@RequestBody Shift shift) {
        return shiftService.saveShift(shift);
    }

    @DeleteMapping("/{id}")
    public void deleteShift(@PathVariable UUID id) {
        shiftService.deleteShift(id);
    }
}
