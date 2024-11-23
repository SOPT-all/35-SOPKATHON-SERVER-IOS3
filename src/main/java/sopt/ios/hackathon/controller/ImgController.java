package sopt.ios.hackathon.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sopt.ios.hackathon.service.ImgService;

import java.io.IOException;

@RestController
public class ImgController {
    private final ImgService imgService;

    @Value("$${spring.file.upload.path}")
    private String serverUrl;

    public ImgController(ImgService imgService) {
        this.imgService = imgService;
    }

    @PostMapping("/img/upload")
    public ResponseEntity<String> uploadImg(@RequestParam("file") MultipartFile file) {
        try {
            String fileUrl = imgService.saveImg(file, serverUrl);
            return ResponseEntity.ok("이미지 업로드 성공 : " + fileUrl);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("이미지 업로드 실패: " + e.getMessage());
        }
    }
}
