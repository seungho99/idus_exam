package com.example.project1.user;

import com.example.project1.user.model.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/verify")
    public void verify(String uuid) {
        userService.verify(uuid);
    }

    @PostMapping("/signup")
    public void signup(@RequestBody UserDto.SignupRequest dto) {
        userService.signup(dto);
    }
}
