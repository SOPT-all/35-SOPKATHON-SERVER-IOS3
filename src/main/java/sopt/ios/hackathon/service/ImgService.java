package sopt.ios.hackathon.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sopt.ios.hackathon.model.entity.Img;
import sopt.ios.hackathon.repository.ImgRepository;

import java.io.File;
import java.io.IOException;

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
