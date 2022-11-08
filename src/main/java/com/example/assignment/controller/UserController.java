package com.example.assignment.controller;

import com.example.assignment.domain.vo.UserVO;
import com.example.assignment.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user/*")
public class UserController {
    private final UserService userService;

    // 회원가입
    @ResponseBody
    @PostMapping("/signUp")
    public RedirectView userSignUp(UserVO userVO){
        log.info("-------------------------------------------------");
        log.info(userVO.getUserId());
        log.info("-------------------------------------------------");
        log.info(userVO.getUserPw());
        userService.userSignUp(userVO);
        return new RedirectView("/user/goLogin");
    }
    
    // 아이디 중복 검사
    @GetMapping("/idCheck")
    @ResponseBody
    public boolean idCheck(String userId){
        log.info("-------------------------------------------------");
        log.info(userService.idCheck(userId)+"");
        return userService.idCheck(userId);

    }

    // 로그인
    @PostMapping("/login")
    public String userLogin(UserVO userVO, HttpServletResponse response, Model model) {

        model.addAttribute("userId",userService.login(userVO).getUserId());
        model.addAttribute("userLastLogin",userService.login(userVO).getUserLastLogin());

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat lastLogin = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        String strLastLogin = lastLogin.format(calendar.getTime());
        calendar.add(calendar.MINUTE,10);
        String cookieTime = simpleDateFormat.format(calendar.getTime());

//        String dbLastLogin = URLEncoder.encode(userService.login(userVO).getUserLastLogin(),"UTF-8");

        int cookiesLen = 2;
        Cookie[] LOGIN_USER = new Cookie[cookiesLen];
        LOGIN_USER[0] = new Cookie("userId",userService.login(userVO).getUserId());
        LOGIN_USER[1] = new Cookie("cookieTime",cookieTime);
//        Cookie LOGIN_USER_ID = new Cookie("userId",userService.login(userVO).getUserId());
//        Cookie LOGIN_USER_LAST_LOGIN = new Cookie("userLastLogin",dbLastLogin);
//        Cookie LOGIN_USER_COOKIE_TIME = new Cookie("cookieTime",cookieTime);

        for (int i = 0; i < cookiesLen; i++){
            LOGIN_USER[i].setMaxAge(60*10);
            response.addCookie(LOGIN_USER[i]);
        }

//        LOGIN_USER_ID.setMaxAge(60*10);
//        response.addCookie(LOGIN_USER_ID);
//
//        LOGIN_USER_LAST_LOGIN.setMaxAge(60*10);
//        response.addCookie(LOGIN_USER_LAST_LOGIN);
//
//        LOGIN_USER_COOKIE_TIME.setMaxAge(60*10);
//        response.addCookie(LOGIN_USER_COOKIE_TIME);

        if (userService.login(userVO).getUserId().equals(null)){
            return "login";
        }

        userVO.setUserLastLogin(strLastLogin);
        userService.userLastLogin(userVO);
        return "main";
    }

    //로그아웃
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for (int i =0; i< cookies.length;i++){
                cookies[i].setMaxAge(0);
                response.addCookie(cookies[i]);
            }
        }
        return "redirect:http://localhost:11070/user/goLogin";
    }

    // 회원가입 페이지로 이동
    @GetMapping("/goSignUp")
    public String goSignUp(){
        return "signUp";
    }

    // 로그인 페이지로 이동
    @GetMapping("/goLogin")
    public String goLogin(){
        return "login";
    }

    // 회원가입 페이지로 이동
    @GetMapping("/goMain")
    public String goMain(){
        return "main";
    }


}
