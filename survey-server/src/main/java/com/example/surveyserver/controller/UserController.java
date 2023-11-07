package com.example.surveyserver.controller;

import com.example.surveyserver.config.TimeConfig;
import com.example.surveyserver.entity.Branch;
import com.example.surveyserver.entity.Image;
import com.example.surveyserver.entity.User;
import com.example.surveyserver.repository.BranchRepository;
import com.example.surveyserver.repository.ImageRepository;
import com.example.surveyserver.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.StringUtils;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
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

    @PostMapping("/api/check")
    public boolean checkEmail(String email, int branchId) {
        User user = userRepository.findByEmailAndBranchId(email, branchId);
        if (user != null) {
            return true;
        }
        return false;
    }

    /**
     * 전체 등록 유저수 조회
     *
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

    @GetMapping("/api/stat")
    public Object resultTest() {
        Map<String, Long> results = new HashMap<String, Long>();

        long krTotalCnt = 0;
        long vnTotalCnt = 0;
        long krDailyCnt = 0;
        long vnDailyCnt = 0;
        Date now = new Date();

        krTotalCnt = userRepository.countAllByBranchId(1);
        vnTotalCnt = userRepository.countAllByBranchId(2);
        krDailyCnt = userRepository.countAllByDateCreatedBetweenAndBranchId(DateUtils.clearDate(now), DateUtils.clearDate(DateUtils.addDay(now, 1)), 1);
        vnDailyCnt = userRepository.countAllByDateCreatedBetweenAndBranchId(DateUtils.clearDate(now), DateUtils.clearDate(DateUtils.addDay(now, 1)), 2);

        results.put("krTotalCnt", krTotalCnt);
        results.put("vnTotalCnt", vnTotalCnt);
        results.put("krDailyCnt", krDailyCnt);
        results.put("vnDailyCnt", vnDailyCnt);

        return results;
    }

    @GetMapping("/api/getAllUserData")
    public List<User> getAllData() {
        List<User> results;
        results = userRepository.findAllBy();

        return results;
    }

    @GetMapping("/api/getBranchUser")
    public List<User> getBranchUser(@RequestParam("branchId") int branchId) {
        List<User> results;
        results = userRepository.findAllByBranchId(branchId);

        return results;
    }


    @PostMapping("/api/imageUpload")
    public String uploadImage(@RequestParam("image") MultipartFile image, @RequestParam("email") String email, @RequestParam("branchId") int branchId) {
        if (!image.isEmpty()) {
            try {
                Branch branch = branchRepository.findById(branchId);
                // 이미지를 저장할 디렉토리 경로 설정
                /*String directoryPath = "C:\\Users\\JB\\Documents\\namecardimgs";*/
                String directoryPath = branch.getImgPath();
                File directory = new File(directoryPath);
                if (!directory.exists()) {
                    directory.mkdirs();
                }

                // 이미지 파일 저장
                /*File imageFile = new File(directory, image.getOriginalFilename());*/
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
    @PostMapping("/api/login")
    public boolean login(@RequestBody Map<String, String> params) {
        User checkUser = userRepository.findByNameAndEmail(params.get("name"), params.get("email"));
        if (checkUser.getIsAdmin() == 1) {
            return true;
        }
        return false;
    }
}

