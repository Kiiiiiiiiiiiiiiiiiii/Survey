package com.example.surveyserver.controller;

import com.example.surveyserver.config.TimeConfig;
import com.example.surveyserver.entity.Branch;
import com.example.surveyserver.entity.Image;
import com.example.surveyserver.entity.User;
import com.example.surveyserver.repository.BranchRepository;
import com.example.surveyserver.repository.ImageRepository;
import com.example.surveyserver.repository.UserRepository;
import com.example.surveyserver.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Array;
import java.text.ParseException;
import java.util.*;

@RestController
public class BranchController {
    @Autowired
    BranchRepository branchRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/api/branches")
    public List<Object> getBranches() {

        List<Branch> branches = branchRepository.findAll();
        List<Object> test = new ArrayList();
        Date now = new Date();

        for (int i = 0; i < branches.size(); i++) {
            Map<String, Object> dataList = new HashMap<>();
            long todayCnt = userRepository.countAllByDateCreatedBetweenAndBranchId(DateUtils.clearDate(now), DateUtils.clearDate(DateUtils.addDay(now, 1)), branches.get(i).getId());
            long totalCnt = userRepository.countAllByBranchId(branches.get(i).getId());
            dataList.put("id", branches.get(i).getId());
            dataList.put("name", branches.get(i).getName());
            dataList.put("code", branches.get(i).getCode());
            dataList.put("todayCnt", todayCnt);
            dataList.put("totalCnt", totalCnt);
            test.add(i, dataList);
        }

        return test;
    }
}

