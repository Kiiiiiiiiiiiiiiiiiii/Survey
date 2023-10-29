package com.example.surveyserver.repository;

import com.example.surveyserver.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BranchRepository extends JpaRepository<Branch, Integer> {
    /**분관 Id를 받아서 분관명 return*/
    Branch findById(int id);

    @Override
    List<Branch> findAll();
}
