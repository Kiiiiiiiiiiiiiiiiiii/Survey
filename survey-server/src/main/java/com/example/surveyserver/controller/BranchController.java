package com.example.surveyserver.controller;

import com.example.surveyserver.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BranchController {
    @Autowired
    private BranchService branchService;

    @GetMapping("/api/branches")
    public List<Object> getBranches() {
        return branchService.getBranches();
    }
}

