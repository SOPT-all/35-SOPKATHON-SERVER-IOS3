package sopt.ios.hackathon.model.dto;

public record GetDrinkResponse(
        String userId,
        int drinkCnt,
        boolean isExceed,
        int limitDrinkCnt
) {

}
