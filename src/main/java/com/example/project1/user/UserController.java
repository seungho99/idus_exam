package com.example.project1.user;

import com.example.project1.user.model.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Operation(summary= "이메일 인증 과정", description = "사용자가 회원가입을 할 때 이메일로 인증을 하는 기능입니다.")
    @GetMapping("/verify")
    public void verify(String uuid) {
        userService.verify(uuid);
    }

    @Operation(summary = "회원가입", description = "사용자가 회원가입을 하는 기능입니다.")
    @PostMapping("/signup")
    public void signup(@RequestBody UserDto.SignupRequest dto) {
        userService.signup(dto);
    }

    @Operation(summary = "단일 회원 상세 정보 조회", description = "단일 회원의 상세정보를 조회하는 기능입니다.")
    @GetMapping("/read/{idx}")
    public ResponseEntity<UserDto.ReadResponse> read(@PathVariable Long idx) {
        UserDto.UserResponse result = userService.read(idx);

        return ResponseEntity.ok(UserDto.ReadResponse.success(result));
    }

}
