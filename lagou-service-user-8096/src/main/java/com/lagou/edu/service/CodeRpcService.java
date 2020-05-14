package com.lagou.edu.service;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


public interface CodeRpcService {

    // Feign要做的事情就是，拼装url发起请求
    // 我们调用该方法就是调用本地接口方法，那么实际上做的是远程请求
    @GetMapping("/code/{userId}")
    Integer findDefaultResumeState(@PathVariable("userId") Long userId);

    @GetMapping("/code/generate")
    Integer generate(@RequestParam String email);
}
