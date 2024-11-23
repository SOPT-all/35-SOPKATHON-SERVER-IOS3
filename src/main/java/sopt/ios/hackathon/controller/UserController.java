package sopt.ios.hackathon.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sopt.ios.hackathon.model.dto.GetDrinkResponse;
import sopt.ios.hackathon.service.UserService;

@Validated
@RestController
@RequestMapping("/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }
    @GetMapping("/drink")
    public ResponseEntity<GetDrinkResponse> getDrinkStatus(
            @RequestHeader("userId") final Long userId
    ) {

        return ResponseEntity.ok(userService.fetchDrinkStatus());
    }

}
