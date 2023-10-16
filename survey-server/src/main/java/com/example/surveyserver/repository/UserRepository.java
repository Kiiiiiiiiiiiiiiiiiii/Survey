package com.example.surveyserver.repository;

import com.example.surveyserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByNameAndEmail(String name, String email);
    User findByEmail(String email);

    Long countAllBy();

    Long countAllByDateCreatedBetween(Date date1, Date date2);
}
