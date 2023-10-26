package com.example.surveyserver.repository;

import com.example.surveyserver.entity.Branch;
import com.example.surveyserver.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Integer> {
    Image findById(int id);
    List<Image> findAllByBranchId(int branchId);
}
