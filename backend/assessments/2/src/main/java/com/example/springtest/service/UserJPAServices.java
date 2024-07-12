package com.example.springtest.service;

import com.example.springtest.entities.Address;
import com.example.springtest.entities.Cart;
import com.example.springtest.entities.Product;
import com.example.springtest.entities.UserJPA;
import com.example.springtest.repository.CartRepository;
import com.example.springtest.repository.ProductRepository;
import com.example.springtest.repository.UserJPARepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserJPAServices {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserJPARepository userJPARepository;
    @Autowired
    private ProductService productService;

    public List<UserJPA> getAllUsers(){
        return userJPARepository.findAll();
    }

    public void saveUserJPA(UserJPA user){
        userJPARepository.save(user);
    }

    public UserJPA getUserById(Long id){
        UserJPA user = null;
        for(UserJPA p : userJPARepository.findAll()){
            if(p.getId() == id){
                user = p;
                break;
            }
        }
        return user;
    }

    public void userDetails(Long id){
        List<Address> addresses = userJPARepository.getAddresses(id);
        log.info("User details : ");
        log.info(userJPARepository.findById(id).toString());
        log.info(addresses.toString());
    }

    public void buyProductById(Long id){
        Cart cart = userJPARepository.getCart(id);
        for(Cart p : cartRepository.findAll()){
            if(p.getQuantity() > getQuantity(p.getId())){
                log.info("Not enough quantity of product in inventory to buy");
                return;
            }
        }
        // Check if the cart is not null
        if (cart != null) {
            updateInventory();
            cartRepository.delete(cart);
            cartRepository.save(new Cart());
        }

    }

    public int getQuantity(Long id){
        Product product = null;
        for(Product p : productRepository.findAll()){
            if(p.getId() == id){
                product = p;
                break;
            }
        }
        if(product == null){
            return 0;
        }
        return product.getQuantity();
    }

    public void updateInventory(){
        List<Cart> products = cartRepository.findAll();
        for(Cart p : products){
            Product product = productService.getProductById(p.getId());
            product.setQuantity(product.getQuantity() - p.getQuantity());
            productRepository.save(product);
        }
    }
}
