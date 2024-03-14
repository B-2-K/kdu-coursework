package com.example.springjpa.controllers;

import com.example.springjpa.entities.Tenant;
import com.example.springjpa.services.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tenants")
public class TenantController {
    @Autowired
    private TenantService tenantService;

    @GetMapping
    public List<Tenant> getAll(){
        return tenantService.getAllTenants();
    }

    @PostMapping
    public ResponseEntity<Tenant> createTenant(@RequestBody Tenant tenant){
        tenantService.saveTenant(tenant);
        return new ResponseEntity<Tenant>(tenant, HttpStatus.CREATED);
    }
}
