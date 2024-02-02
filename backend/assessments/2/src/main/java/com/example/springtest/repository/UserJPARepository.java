package com.example.springtest.repository;

import com.example.springtest.entities.UserJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJPARepository extends JpaRepository<UserJPA, Long> {
}
