package com.example.springtest.controller;

import com.example.springtest.entities.Cart;
import com.example.springtest.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("carts")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping
    public List<Cart> getAll(){
        return cartService.getAllCart();
    }

    @PostMapping
    public ResponseEntity<Cart> createProduct(@RequestBody Cart cart){
        cartService.saveCart(cart);
        return new ResponseEntity<Cart>(cart, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cart> updateProduct(@PathVariable Long id, @RequestBody Cart updatedCart) {
        Cart existingCart = cartService.getCartById(id);
        if (existingCart!= null) {
            existingCart.setQuantity(updatedCart.getQuantity());
        }
        return new ResponseEntity<Cart>(existingCart, HttpStatus.OK);
    }
}
