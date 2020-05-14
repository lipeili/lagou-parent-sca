package com.lagou.edu.controller;

import com.lagou.edu.service.CodeRpcService;
import com.lagou.edu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private CodeRpcService codeRpcService;

    /**
     * 用户注册
     * @param email
     * @param code
     * @param password
     * @return
     */
    @PostMapping("/register")
    public String register(String email, String code, String password) {
        return userService.register(email, password, code);
    }

    /**
     * 用户登陆
     * @param email
     * @param password
     * @return
     */
    @PostMapping("/login")
    public String login(String email, String password) {
        return userService.login(email, password);
    }

    @PostMapping("/123")
    public String login2(String email, String password) {
        return userService.login(email, password);
    }


    @GetMapping("/generate")
    public Integer generate(String email) {
        return codeRpcService.generate(email);
    }
}
