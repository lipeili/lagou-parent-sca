package com.lagou.edu.service;

public interface MailService {


    /**
     * 发送纯文本邮件
     * @param toAddr 发送给谁
     * @param title 标题
     * @param content 内容
     */
    void sendTextMail(String toAddr, String title, String content);

}
