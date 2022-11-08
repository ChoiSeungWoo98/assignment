package com.example.assignment.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserVO {
    private String userId;          // 유저 아이디
    private String userPw;          // 유저 비밀번호
    private String userLastLogin;   // 유저 마지막 로그인 시간
}
