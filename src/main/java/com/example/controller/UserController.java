package com.example.controller;

import com.example.pojo.Result;
import com.example.pojo.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    public Result add(@RequestBody User user){
        Date date=new Date();
        DateFormat df=new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        user.setRegistrationTime(df.format(date));
        String password=user.getPassword();
        String salt="143234";
        password=password+salt;
        password= DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
        user.setPassword(password);
        try {
            userService.add(user);
            return new Result(200,"用户注册成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(500,"注册失败",e);
        }

    }
    @RequestMapping("/login")
    public Result login(@RequestBody User user, HttpSession session){
        User result=userService.selectByUsername(user.getUsername());

        if(result==null){
            return new Result(300,"该用户未注册");
        }
        String password= user.getPassword();
        String salt="143234";
        password=password+salt;
        password=DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
        if(result.getPassword().equals(password)){
            session.setAttribute("uid",result.getUid());
            return new Result(200,"登录成功");
        }else {
            return new Result(300,"密码失败");
        }
    }

}

