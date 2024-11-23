package sopt.ios.hackathon.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sopt.ios.hackathon.model.dto.GetDrinkResponse;
import sopt.ios.hackathon.model.dto.PostUserRequest;
import sopt.ios.hackathon.model.dto.PostUserResponse;
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
        return ResponseEntity.ok(userService.fetchDrinkStatus(userId));
    }

    @PatchMapping("/drink")
    public ResponseEntity<GetDrinkResponse> patchDrinkStatus(
            @RequestHeader("userId") final Long userId
    ) {
        return ResponseEntity.ok(userService.patchDrinkStatus(userId));
    }
    @PostMapping()
    public ResponseEntity<PostUserResponse> createUser(
            @Valid @RequestBody final PostUserRequest postUserRequest
    ) {

        return ResponseEntity.ok(userService.createUser(postUserRequest.name(), postUserRequest.drinkLimit()));
    }

    @DeleteMapping()
    public void  deleteUser(
            @RequestHeader("userId") final Long userId
    ) {
       userService.removeUser(userId);
    }

}
