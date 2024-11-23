package sopt.ios.hackathon.model.dto;

import sopt.ios.hackathon.model.entity.Img;

public record GetRandomResponse (Long imgId, String imgUrl){
    public GetRandomResponse fromEntity(Img img) {
        return new GetRandomResponse(img.getId(), img.getImgUrl());
    }
}
