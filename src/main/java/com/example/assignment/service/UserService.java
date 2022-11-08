package com.example.assignment.service;

import com.example.assignment.domain.vo.UserVO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    // 회원가입
    public void userSignUp(UserVO userVO);

    // 아이디 중복 확인
    public boolean idCheck(String userId);

    // 유저 전체 정보 불러오기
    public String userSelect();

    // 로그인
    public UserVO login(UserVO userVO);

    //로그인 시간 갱신
    public void userLastLogin(UserVO userVO);
}
