package com.ghc.starter.controller;

import com.ghc.starter.domain.model.User;
import com.ghc.starter.result.Result;
import com.ghc.starter.service.UserService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/center")
public class centercontroller {
    @Autowired
    private UserService userService;
    @RequestMapping("/sayhello")
    public String hello(Model model){
        model.addAttribute("name","frank");
        return "hello";
    }

    @RequestMapping("/getAllUsers")
    public Result<List<User>> getAllUsers(){
        List<User> userList = null;
        Result<List<User>> listResult=null;
        try{userList = userService.getAllUsers();
//            int a = 1/0; //测试下异常
            listResult = Result.success(userList);
        }catch(Exception e){
            listResult = Result.error();
        }
        return listResult;
    }
}
