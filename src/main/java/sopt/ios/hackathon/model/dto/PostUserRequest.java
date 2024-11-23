package sopt.ios.hackathon.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PostUserRequest(
        @NotBlank(message = "이름은 빈 값일 수 없습니다")
        String name,

        @NotNull()
        double drinkLimit
) {

}
