package com.example.surveyserver.repository;

import com.example.surveyserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    /**이메일 중복 체크*/
    User findByEmailAndBranchId(String email, int branchId);
    User findByNameAndEmail(String name, String email);

    /**유저 전체 카운트*/
    Long countAllBy();

    /** 당일 전체 카운트*/
    Long countAllByDateCreatedBetween(Date date1, Date date2);

    /**유저 분관별 당일 카운트*/
    Long countAllByDateCreatedBetweenAndBranchId(Date date1, Date date2, int branchId);

    /**유저 분관별 전체카운트*/
    Long countAllByBranchIdAndIsAdmin(int branchId, int isAdmin);

    List<User> findAllBy();
    List<User> findAllByBranchId(int branchId);
}
