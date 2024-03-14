package com.example.springjpa.services;

import com.example.springjpa.entities.Tenant;
import com.example.springjpa.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TenantService {
    @Autowired
    private TenantRepository tenantRepository;

    public List<Tenant> getAllTenants(){
        return tenantRepository.findAll();
    }

    public void saveTenant(Tenant tenant){
        tenantRepository.save(tenant);
    }
}
