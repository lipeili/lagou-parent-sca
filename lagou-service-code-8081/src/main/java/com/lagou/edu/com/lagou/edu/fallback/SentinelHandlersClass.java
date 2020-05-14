package com.lagou.edu.com.lagou.edu.fallback;


import com.alibaba.csp.sentinel.slots.block.BlockException;

public class SentinelHandlersClass {

    public static String handleException(String email, BlockException
            blockException) {
        blockException.printStackTrace();

        return "-100";
    }
    public static String handleError(String emai, BlockException
            blockException) {
        blockException.printStackTrace();
        return "-500";
    }

}
