package com.example.surveyserver.controller;

import com.example.surveyserver.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageController {
    @Autowired
    ImageService imageService;

    /**
     * 이미지 압축다운로드 코드
     *
     */
    @PostMapping("/api/downloadZipfile")
    public ResponseEntity<FileSystemResource> downloadZipFile(@RequestParam("branchId") int branchId) {
        return imageService.downloadZipFile(branchId);
    }
}

