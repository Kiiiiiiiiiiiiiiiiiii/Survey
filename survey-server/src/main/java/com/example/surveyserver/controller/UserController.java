package com.example.surveyserver.controller;

import com.example.surveyserver.config.TimeConfig;
import com.example.surveyserver.entity.Branch;
import com.example.surveyserver.entity.Image;
import com.example.surveyserver.entity.User;
import com.example.surveyserver.repository.BranchRepository;
import com.example.surveyserver.repository.ImageRepository;
import com.example.surveyserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

import com.example.surveyserver.util.DateUtils;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BranchRepository branchRepository;
    @Autowired
    ImageRepository imageRepository;
    @Autowired
    TimeConfig timeConfig;

    /***
     * 개인정보수집 저장
     * @param params
     * @return
     * @throws ParseException
     */
    @PostMapping("/api/submit")
    @Transactional
    public Boolean submit(@RequestBody Map<String, String> params) throws ParseException {
        String userEmail = params.get("email");
        int branchId = Integer.parseInt(params.get("branchId"));
        if (!checkEmail(userEmail, branchId)) {
            Branch branch = branchRepository.findById(branchId);
            String branchCode = branch.getCode();

            User newUser = new User();
            newUser.setName(params.get("name"));
            newUser.setCompany(params.get("company"));
            newUser.setEmail(userEmail);
            newUser.setIndustry(params.get("industry"));
            newUser.setDateCreated(timeConfig.getFormatTime());
            newUser.setBranchId(branchId);
            newUser.setBranchCode(branchCode);
            newUser.setPhone(params.get("phone"));
            userRepository.save(newUser);
            return true;
        }
        return false;
    }

    /***
     * 분관별 중복이메일 체크
     * @param email
     * @param branchId
     * @return
     */
    @PostMapping("/api/check")
    public boolean checkEmail(String email, int branchId) {
        User user = userRepository.findByEmailAndBranchId(email, branchId);
        if (user != null) {
            return true;
        }
        return false;
    }

    /***
     * 해당 분관 전체 이용자 정보
     * @param branchId
     * @return
     */
    @GetMapping("/api/getBranchUser")
    public List<User> getBranchUser(@RequestParam("branchId") int branchId) {
        List<User> results;
        results = userRepository.findAllByBranchId(branchId);

        return results;
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
        if (!image.isEmpty()) {
            try {
                Branch branch = branchRepository.findById(branchId);
                // 이미지를 저장할 디렉토리 경로 설정
                String directoryPath = branch.getImgPath();
                File directory = new File(directoryPath);
                if (!directory.exists()) {
                    directory.mkdirs();
                }

                // 이미지 파일 저장
                String extension = StringUtils.getFilenameExtension(image.getOriginalFilename());
                String returnFileName = email + '.' + extension;
                File imageFile = new File(directory, returnFileName);
                image.transferTo(imageFile);

                User user = userRepository.findByEmailAndBranchId(email, branchId);
                Image newImage = new Image();

                newImage.setName(returnFileName);
                newImage.setBranchId(branchId);
                newImage.setUserId(user.getId());
                imageRepository.save(newImage);

                // 이미지 업로드 성공
                return "이미지 업로드에 성공했습니다.";
            } catch (IOException e) {
                // 이미지 업로드 실패
                e.printStackTrace();
                return "이미지 업로드에 실패했습니다.";
            }
        } else {
            return "업로드할 이미지를 선택하세요.";
        }
    }

    /***
     * 관리자 로그인
     * @param params
     * @return
     */
    @PostMapping("/api/login")
    public boolean login(@RequestBody Map<String, String> params) {
        User checkUser = userRepository.findByNameAndEmail(params.get("name"), params.get("email"));
        if (checkUser.getIsAdmin() == 1) {
            return true;
        }
        return false;
    }
}

