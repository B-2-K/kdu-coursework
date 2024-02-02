package com.example.springtest.repository;

import com.example.springtest.entities.Address;
import com.example.springtest.entities.Cart;
import com.example.springtest.entities.UserJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserJPARepository extends JpaRepository<UserJPA, Long> {
    @Query("SELECT c FROM Cart c WHERE c.cart_id = :userId")
    public Cart getCart(@Param("userId") Long userId);

    @Query("SELECT c FROM Address c WHERE c.address_id = :Id")
    public List<Address> getAddresses(@Param("Id") Long Id);
}
