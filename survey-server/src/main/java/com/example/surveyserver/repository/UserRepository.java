package com.example.surveyserver.repository;

import com.example.surveyserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByNameAndEmail(String name, String email);
    User findByEmail(String email);
}
