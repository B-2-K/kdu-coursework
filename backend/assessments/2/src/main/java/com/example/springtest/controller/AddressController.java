package com.example.springtest.controller;

import com.example.springtest.entities.Address;
import com.example.springtest.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping
    public List<Address> getAll(){
        return addressService.getAllAddresses();
    }

    @PostMapping
    public ResponseEntity<Address> createProduct(@RequestBody Address address){
        addressService.saveProduct(address);
        return new ResponseEntity<Address>(address, HttpStatus.CREATED);
    }
}
