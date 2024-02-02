package com.example.springtest.service;
import com.example.springtest.entities.Product;
import com.example.springtest.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public void saveProduct(Product tenant){
        productRepository.save(tenant);
    }
}
