package com.example.surveyserver.controller;

import com.example.surveyserver.config.TimeConfig;
import com.example.surveyserver.entity.User;
import com.example.surveyserver.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    TimeConfig timeConfig;
    @PostMapping("/api/submit")
    @Transactional
    public ResponseEntity submit(@RequestBody Map<String, String> params) throws ParseException {
        User newUser = new User();
        newUser.setName(params.get("name"));
        newUser.setCompany(params.get("company"));
        newUser.setEmail(params.get("email"));
        newUser.setIndustry(params.get("industry"));
        newUser.setDateCreated(timeConfig.getVTNTime());
        userRepository.save(newUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/api/check")
    public boolean checkEmail(@RequestBody Map<String, String> params) {
        User user = userRepository.findByEmail(params.get("email"));
        if (user != null){
            return false;
        }
        return true;
    }

}
