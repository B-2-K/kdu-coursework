package com.kdu.smarthome.repository;

import com.kdu.smarthome.entity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel,Long> {

    @Query
    Optional<UserModel> findByUsername(String username);


}
