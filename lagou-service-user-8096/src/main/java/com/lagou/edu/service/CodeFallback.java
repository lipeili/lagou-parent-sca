//package com.lagou.edu.service;
//
//
//import org.springframework.stereotype.Component;
//
///**
// * 降级回退逻辑需要定义一个类，实现FeignClient接口，实现接口中的方法
// *
// *
// */
//@Component("codeFallback")  // 别忘了这个注解，还应该被扫描到
//public class CodeFallback implements CodeRpcService {
//
//    @Override
//    public Integer findDefaultResumeState(Long userId) {
//        return 0;
//    }
//
//    @Override
//    public Integer generate(String email) {
//        return null;
//    }
//}
