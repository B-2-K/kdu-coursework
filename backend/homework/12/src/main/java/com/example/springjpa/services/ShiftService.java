package com.example.springjpa.services;

import com.example.springjpa.entities.Shift;
import com.example.springjpa.repository.ShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ShiftService {

    @Autowired
    private ShiftRepository shiftRepository;

    public List<Shift> getAllShifts() {
        return shiftRepository.findAll();
    }

    public Optional<Shift> getShiftById(UUID id) {
        return shiftRepository.findById(id);
    }

    public Shift saveShift(Shift shift) {
        return shiftRepository.save(shift);
    }

    public void deleteShift(UUID id) {
        shiftRepository.deleteById(id);
    }

    public List<Shift> findTop3ShiftsByDateRange(LocalDate startDate, LocalDate endDate) {
        Pageable pageable = PageRequest.of(0, 3); // Retrieve top 3
        return shiftRepository.findTop3ShiftsByDateRange(startDate, endDate, pageable);
    }
}
