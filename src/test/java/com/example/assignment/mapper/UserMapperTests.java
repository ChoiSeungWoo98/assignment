package com.example.assignment.mapper;

import com.example.assignment.domain.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class UserMapperTests {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    public UserVO userVO;


    @Test
    public void selectUser(){
//        userVO.setUserId("admin");
//        userVO.setUserPw("123");

        log.info("--------------------");
        log.info(userMapper.select());
        log.info("--------------------");
    }
}
