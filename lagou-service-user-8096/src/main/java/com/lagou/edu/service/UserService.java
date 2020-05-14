package com.lagou.edu.service;

public interface UserService {

    String login(String email, String password);

    String register(String email, String password, String code);

}
