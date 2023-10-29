package com.example.surveyserver.controller;

import com.example.surveyserver.entity.Branch;
import com.example.surveyserver.entity.Image;
import com.example.surveyserver.repository.BranchRepository;
import com.example.surveyserver.repository.ImageRepository;
import com.example.surveyserver.util.DateUtils;
import com.example.surveyserver.util.FileZipUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.jni.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
public class FileController {
    @Autowired
    BranchRepository branchRepository;
    @Autowired
    ImageRepository imageRepository;

    /**
     * 이미지 압축다운로드 코드
     * 작동은 하나 코드 정리 필요
     */
    @PostMapping("/api/downloadZipfile")
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

