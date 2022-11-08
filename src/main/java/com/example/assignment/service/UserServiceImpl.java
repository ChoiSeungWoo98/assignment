package com.example.assignment.service;

import com.example.assignment.domain.dao.UserDAO;
import com.example.assignment.domain.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Qualifier("user")
@Primary
public class UserServiceImpl implements UserService{
    private final UserDAO userDAO;

    @Override
    public void userSignUp(UserVO userVO){userDAO.signUp(userVO);}

    @Override
    public boolean idCheck(String userId){return userDAO.idCheck(userId);}

    @Override
    public String userSelect(){return userDAO.select();}

    @Override
    public UserVO login(UserVO userVO){return userDAO.login(userVO);}

    @Override
    public void userLastLogin(UserVO userVO){userDAO.userLastLogin(userVO);}
}
