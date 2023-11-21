package com.example.surveyserver.service;

import com.example.surveyserver.entity.Branch;
import com.example.surveyserver.repository.BranchRepository;
import com.example.surveyserver.repository.UserRepository;
import com.example.surveyserver.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BranchService {
    @Autowired
    BranchRepository branchRepository;
    @Autowired
    UserRepository userRepository;

    public List<Object> getBranches() {
        List<Branch> branches = branchRepository.findAll();
        List<Object> Data = new ArrayList();
        Date now = new Date();

        for (int i = 0; i < branches.size(); i++) {
            Map<String, Object> dataList = new HashMap<>();
            long todayCnt = userRepository.countAllByDateCreatedBetweenAndBranchId(DateUtils.clearDate(now), DateUtils.clearDate(DateUtils.addDay(now, 1)), branches.get(i).getId());
            long totalCnt = userRepository.countAllByBranchIdAndIsAdmin(branches.get(i).getId(), 0);
            dataList.put("id", branches.get(i).getId());
            dataList.put("name", branches.get(i).getName());
            dataList.put("code", branches.get(i).getCode());
            dataList.put("todayCnt", todayCnt);
            dataList.put("totalCnt", totalCnt);
            Data.add(i, dataList);
        }

        return Data;
    }
}
