package com.example.surveyserver.service;

import com.example.surveyserver.entity.Branch;
import com.example.surveyserver.entity.Image;
import com.example.surveyserver.entity.User;
import com.example.surveyserver.repository.BranchRepository;
import com.example.surveyserver.repository.ImageRepository;
import com.example.surveyserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private ImageRepository imageRepository;

    public Boolean submit(Map<String, String> params) throws ParseException {
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
            newUser.setDateCreated(new Date());
            newUser.setBranchId(branchId);
            newUser.setBranchCode(branchCode);
            newUser.setPhone(params.get("phone"));
            userRepository.save(newUser);
            return true;
        }
        return false;
    }

    public boolean checkEmail(String email, int branchId) {
        User user = userRepository.findByEmailAndBranchId(email, branchId);
        return user != null;
    }

    public List<User> getBranchUser(int branchId) {
        return userRepository.findAllByBranchId(branchId);
    }

    public String uploadImage(MultipartFile image, String email, int branchId) {
        if (!image.isEmpty()) {
            try {
                Branch branch = branchRepository.findById(branchId);
                String directoryPath = branch.getImgPath();
                File directory = new File(directoryPath);
                if (!directory.exists()) {
                    directory.mkdirs();
                }

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

                return "이미지 업로드에 성공했습니다.";
            } catch (IOException e) {
                e.printStackTrace();
                return "이미지 업로드에 실패했습니다.";
            }
        } else {
            return "업로드할 이미지를 선택하세요.";
        }
    }

    public boolean login(Map<String, String> params) {
        User checkUser = userRepository.findByNameAndEmail(params.get("name"), params.get("email"));
        return checkUser != null && checkUser.getIsAdmin() == 1;
    }
}
