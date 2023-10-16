package com.example.surveyserver.controller;

import com.example.surveyserver.config.TimeConfig;
import com.example.surveyserver.entity.User;
import com.example.surveyserver.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Map;
import com.example.surveyserver.util.DateUtils;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    TimeConfig timeConfig;
    @PostMapping("/api/submit")
    @Transactional
    public Boolean submit(@RequestBody Map<String, String> params) throws ParseException {
        String userEmail = params.get("email");
        if (!checkEmail(userEmail)) {
            User newUser = new User();
            newUser.setName(params.get("name"));
            newUser.setCompany(params.get("company"));
            newUser.setEmail(userEmail);
            newUser.setIndustry(params.get("industry"));
            newUser.setDateCreated(timeConfig.getVTNTime());
            userRepository.save(newUser);
            return true;
        }
        return false;
    }

    @PostMapping("/api/check")
    public boolean checkEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null){
            return true;
        }
        return false;
    }

    /**
     * 전체 등록 유저수 조회
     * @return result
     */
    @GetMapping("/api/total")
    public long resultTotal() {
        long result = 0;
        result = userRepository.countAllBy();

        return result;
    }

    @GetMapping("/api/today")
    public long resultDaily() {
        long result = 0;
        Date now = new Date();
        result = userRepository.countAllByDateCreatedBetween(DateUtils.clearDate(now), DateUtils.clearDate(DateUtils.addDay(now, 1)));

        return result;
    }

}
