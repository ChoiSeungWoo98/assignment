package com.example.assignment.domain.dao;

import com.example.assignment.domain.vo.UserVO;
import com.example.assignment.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserDAO {
    private final UserMapper userMapper;

    public void signUp(UserVO userVO){
        userMapper.insert(userVO);
    }
    public boolean idCheck(String userId){
        return userMapper.idCheck(userId) == 1;
    }
    public String select(){
        return userMapper.select().toString();
    }
    public UserVO login(UserVO userVO){
        return userMapper.login(userVO);
    }
    public void userLastLogin(UserVO userVO){
        userMapper.userLastLogin(userVO);
    }
}
