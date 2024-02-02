package com.example.springtest.service;

import com.example.springtest.entities.Address;
import com.example.springtest.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public List<Address> getAllAddresses(){
        return addressRepository.findAll();
    }

    public void saveProduct(Address address){
        addressRepository.save(address);
    }
}
