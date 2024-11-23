package sopt.ios.hackathon.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sopt.ios.hackathon.model.dto.GetRandomResponse;
import sopt.ios.hackathon.model.entity.Img;
import sopt.ios.hackathon.repository.ImgRepository;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.*;
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

    public GetRandomResponse getRandomIg() {
        long cnt = imgRepository.count();

        Random rd = new Random();
        Long randId = (long) (rd.nextInt((int) +cnt) + 1);

        //System.out.println(randId);

        Optional<Img> img = imgRepository.findById(randId);

        Img randImg = img.get();

        return new GetRandomResponse(randImg.getId(), randImg.getImgUrl());
    }
}
