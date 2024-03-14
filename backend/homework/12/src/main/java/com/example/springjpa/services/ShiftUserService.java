package com.example.springjpa.services;

import com.example.springjpa.entities.ShiftUser;
import com.example.springjpa.repository.ShiftUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ShiftUserService {

    @Autowired
    private ShiftUserRepository shiftUserRepository;

    public List<ShiftUser> getAllShiftUsers() {
        return shiftUserRepository.findAll();
    }

    public Optional<ShiftUser> getShiftUserById(UUID id) {
        return shiftUserRepository.findById(id);
    }

    public ShiftUser saveShiftUser(ShiftUser shiftUser) {
        return shiftUserRepository.save(shiftUser);
    }

    public List<ShiftUser> getShiftUsersByShiftEndTime(LocalTime timeEnd) {
        return shiftUserRepository.findByShift_TimeEnd(timeEnd);
    }

    public void deleteShiftUser(UUID id) {
        shiftUserRepository.deleteById(id);
    }
}
