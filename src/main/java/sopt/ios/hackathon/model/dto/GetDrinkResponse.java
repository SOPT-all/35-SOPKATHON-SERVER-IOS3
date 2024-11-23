package sopt.ios.hackathon.model.dto;

public record GetDrinkResponse(
        Long userId,
        int drinkCnt,
        boolean isExceed,
        int limitDrinkCnt
) {

}
