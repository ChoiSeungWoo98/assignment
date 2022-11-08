package com.example.assignment.mapper;

import com.example.assignment.domain.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    // 회원 가입
    public void insert(UserVO userVO);
    // 아이디 중복 확인
    public Integer idCheck(String userId);
    // 전체 정보 확인
    public String select();
    // 로그인
    public UserVO login(UserVO userVO);
    // 유저 마지막 로그인 시간
    public void userLastLogin(UserVO userVO);

}
