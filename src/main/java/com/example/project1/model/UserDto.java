package com.example.project1.model;

import lombok.Getter;

public class UserDto {

    @Getter
    public class SignupRequest {
        private String email;
        private String password;
        private String name;

        public User toEntity(String encodedPassword, String type) {
            if (type.equals("USER")) {
                return User.builder()
                        .email(email)
                        .password(encodedPassword)
                        .name(name)
                        .role("USER")
                        .build();
            } else {
                return User.builder()
                        .email(email)
                        .password(encodedPassword)
                        .name(name)
                        .role("INSTRUCTOR")
                        .build();
            }

        }
    }
}
