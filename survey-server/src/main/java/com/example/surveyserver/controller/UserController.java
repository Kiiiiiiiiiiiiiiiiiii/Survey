package com.example.surveyserver.controller;

import com.example.surveyserver.entity.User;
import com.example.surveyserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    /***
     * 개인정보수집 저장
     * @param params
     * @return
     * @throws ParseException
     */
    @PostMapping("/api/submit")
    @Transactional
    public Boolean submit(@RequestBody Map<String, String> params) throws ParseException {
        return userService.submit(params);
    }

    /***
     * 분관별 중복이메일 체크
     * @param email
     * @param branchId
     * @return
     */
    @PostMapping("/api/check")
    public boolean checkEmail(@RequestParam String email, @RequestParam int branchId) {
        return userService.checkEmail(email, branchId);
    }

    /***
     * 해당 분관 전체 이용자 정보
     * @param branchId
     * @return
     */
    @GetMapping("/api/getBranchUser")
    public List<User> getBranchUser(@RequestParam int branchId) {
        return userService.getBranchUser(branchId);
    }

    /***
     * 이미지 저장
     * @param image
     * @param email
     * @param branchId
     * @return
     */
    @PostMapping("/api/imageUpload")
    public String uploadImage(@RequestParam("image") MultipartFile image, @RequestParam("email") String email, @RequestParam("branchId") int branchId) {
        return userService.uploadImage(image, email, branchId);
    }

    /***
     * 관리자 로그인
     * @param params
     * @return
     */
    @PostMapping("/api/login")
    public boolean login(@RequestBody Map<String, String> params) {
        return userService.login(params);
    }
}

