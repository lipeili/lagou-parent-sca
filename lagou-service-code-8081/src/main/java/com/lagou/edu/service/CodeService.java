package com.lagou.edu.service;

import org.apache.dubbo.config.annotation.Service;

@Service
public interface CodeService {

    String generateCode (String email);

    String generateAndSendCode(String email);

    Boolean verifyCode (String email, String code);

}
