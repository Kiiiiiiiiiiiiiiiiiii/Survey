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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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

    @PostMapping("/api/downloadZipfileTEST")
    public void downloadZipFileTEST(@RequestParam("branchId") int branchId, HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/zip");
        response.addHeader("Content-Disposition", "attachment; filename=\"namecard.zip\"");

        FileOutputStream fos = null;
        ZipOutputStream zipOut = null;
        FileInputStream fis = null;

        try {
            zipOut = new ZipOutputStream(response.getOutputStream());
            // DB에 저장되어 있는 파일 목록을 읽어온다.
            List<Image> imgFileList = imageRepository.findAllByBranchId(branchId);
            // 실제 Server에 파일들이 저장된 directory 경로를 구해온다.
            String filePath = getFilePath(branchId);
            // File 객체를 생성하여 List에 담는다.
            /*List<File> fileList = imgFileList.stream().map(imgFileList -> {
                return new File(filePath + "/" + fileInfo.get)
            }).collect(Collectors.toList());*/

            List<File> fileList = new ArrayList<>();
            for (Image imageInfo : imgFileList) {
                String filename = imageInfo.getName();
                File file = new File(filePath + "/" + imageInfo.getName());
                fileList.add(file);
            }


            for (File file : fileList) {
                zipOut.putNextEntry(new ZipEntry(file.getName()));
                fis = new FileInputStream(file);

                StreamUtils.copy(fis, zipOut);
                fis.close();
                zipOut.closeEntry();
            }
            zipOut.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
            try {
                if (fis != null) fis.close();
            } catch (IOException e1) {
                System.out.println(e1.getMessage());/*ignore*/
            }
            try {
                if (zipOut != null) zipOut.closeEntry();
            } catch (IOException e2) {
                System.out.println(e2.getMessage());/*ignore*/
            }
            try {
                if (zipOut != null) zipOut.close();
            } catch (IOException e3) {
                System.out.println(e3.getMessage());/*ignore*/
            }
            try {
                if (fos != null) fos.close();
            } catch (IOException e4) {
                System.out.println(e4.getMessage());/*ignore*/
            }
        }
    }

    /**
     * 이미지 압축다운로드 코드
     * 작동은 하나 코드 정리 필요
     */
    @PostMapping("/api/downloadZipfile")
    public ResponseEntity<FileSystemResource> downloadZipFile(@RequestParam("branchId") int branchId) {
        Branch branch = branchRepository.findById(branchId);
        String imgPath = branch.getImgPath();
        String branchName = branch.getName();
        String filePath = getFilePath(branchId);

        FileZipUtils fileZipUtils = new FileZipUtils();
        fileZipUtils.zip(imgPath, branchName);

        FileSystemResource zipFile = new FileSystemResource(filePath + "/namecardimg_" + branchName + ".zip");

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=namecardimg_" + branchName + ".zip");
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/zip"))
                .body(zipFile);
    }


    public String getFilePath(int branchId) {
        Branch branch = branchRepository.findById(branchId);
        String imgPath = branch.getImgPath();
        return imgPath;
    }
}
