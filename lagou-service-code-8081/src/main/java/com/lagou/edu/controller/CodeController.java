package com.lagou.edu.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.lagou.edu.com.lagou.edu.fallback.SentinelHandlersClass;
import com.lagou.edu.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/code")
public class CodeController {

    @Autowired
    private CodeService codeService;

    /**
     * 验证码生成
     * @param email
     * @return
     */
    @SentinelResource(value = "generate",blockHandlerClass =
            SentinelHandlersClass.class,
            blockHandler = "handleException",fallback =
            "handleError",fallbackClass = SentinelHandlersClass.class)
    @GetMapping("/generate")
    public String generate(String email) {
        return codeService.generateAndSendCode(email);
    }

    /**
     * 验证码校验
     * @param email
     * @param code
     * @return
     */
    @PostMapping("/verify")
    public Boolean verify(String email, String code) {
        return codeService.verifyCode(email, code);
    }

}
