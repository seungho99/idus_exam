package com.example.project1.user.model;

import lombok.Getter;

public class UserDto {

    @Getter
    public static class SignupRequest {
        private String email;
        private String password;
        private String name;
        private String phone;

        public User toEntity(String encodedPassword, String type) {
            if (type.equals("USER")) {
                return User.builder()
                        .email(email)
                        .password(encodedPassword)
                        .name(name)
                        .phone(phone)
                        .role("USER")
                        .build();
            } else {
                return User.builder()
                        .email(email)
                        .password(encodedPassword)
                        .name(name)
                        .phone(phone)
                        .role("INSTRUCTOR")
                        .build();
            }

        }
    }
}
