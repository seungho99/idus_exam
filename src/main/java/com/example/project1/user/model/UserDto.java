package com.example.project1.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class UserDto {

    @AllArgsConstructor
    @Getter
    public static class ReadResponse {
        @Schema(description = "응답 성공 여부", example = "true")
        private Boolean isSuccess;
        private UserResponse result;

        public static ReadResponse success(UserResponse result) {
            return new ReadResponse(true, result);
        }
    }

    @Getter
    public static class UserResponse {
        @Schema(description = "회원번호", example = "1")
        private Long idx;

        @Schema(description = "회원 이메일", example = "test01@test.com")
        private String email;

        @Schema(description = "회원 이름", example = "test01")
        private String name;

        @Schema(description = "회원 전화번호", example = "010-1234-5678")
        private String phone;

        @Schema(description = "회원 등급", example = "USER")
        private String role;

        public static UserResponse from(User user) {
            UserResponse response = new UserResponse();
            response.idx = user.getIdx();
            response.email = user.getEmail();
            response.name = user.getName();
            response.phone = user.getPhone();
            response.role = user.getRole();
            return response;
        }
    }

    @Getter
    public static class SignupRequest {
        @Schema(description = "사용자 이메일", example = "test01@test.com")
        private String email;

        @Schema(description = "사용자 비밀번호", example = "qwer1234")
        private String password;

        @Schema(description = "사용자 이름", example = "test01")
        private String name;

        @Schema(description = "사용자 전화번호", example = "010-1234-5678")
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
