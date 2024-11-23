package sopt.ios.hackathon.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sopt.ios.hackathon.model.dto.GetRandomResponse;
import sopt.ios.hackathon.model.entity.Img;
import sopt.ios.hackathon.repository.ImgRepository;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ImgService {
    private final ImgRepository imgRepository;
    private final FileStorageService fileStorageService;

    public ImgService(ImgRepository imgRepository, FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
        this.imgRepository = imgRepository;
    }

    public String saveImg(MultipartFile file, String serverUrl) throws IOException {
        String fileUrl = fileStorageService.storeFile(file);


        Img img = new Img();
        img.setImgUrl(serverUrl + fileUrl);
        imgRepository.save(img);

        return serverUrl + fileUrl;
    }
}
