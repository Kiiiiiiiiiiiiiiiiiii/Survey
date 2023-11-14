package com.example.surveyserver.service;

import com.example.surveyserver.entity.Branch;
import com.example.surveyserver.repository.BranchRepository;
import com.example.surveyserver.repository.ImageRepository;
import com.example.surveyserver.util.FileZipUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class ImageService {
    @Autowired
    BranchRepository branchRepository;
    @Autowired
    ImageRepository imageRepository;
    public ResponseEntity<FileSystemResource> downloadZipFile(@RequestParam("branchId") int branchId) {
        Branch branch = branchRepository.findById(branchId);
        String imgPath = branch.getImgPath();
        String branchCode = branch.getCode();

        FileZipUtils fileZipUtils = new FileZipUtils();
        fileZipUtils.zip(imgPath, branchCode);

        FileSystemResource zipFile = new FileSystemResource(imgPath + "/namecardimg_" + branchCode + ".zip");

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=namecardimg_" + branchCode + ".zip");
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/zip"))
                .body(zipFile);
    }

}
