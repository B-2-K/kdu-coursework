package com.example.springtest.service;

import com.example.springtest.entities.Cart;
import com.example.springtest.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public List<Cart> getAll(){
        return cartRepository.findAll();
    }

    public void saveCart(Cart cart){
        cartRepository.save(cart);
    }

    public void deleteCart(Cart cart){
        cartRepository.delete(cart);
    }

    public Cart getCartById(Long id){
        Cart cart = null;
        for(Cart c : cartRepository.findAll()){
            if(c.getId().equals(id)){
                cart = c;
            }
        }
        return cart;
    }

    public void deleteProduct(Long id){
        Cart cart = getCartById(id);
        if(cart == null){
            throw new IllegalStateException("Product not found");
        }
        cartRepository.delete(cart);
    }
}
