package com.lagou.edu.service.impl;


import com.lagou.edu.dao.LagouTokenDao;
import com.lagou.edu.constant.Constants;
import com.lagou.edu.pojo.LagouAuthCode;
import com.lagou.edu.pojo.LagouToken;
import com.lagou.edu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private LagouTokenDao lagouTokenDao;

    private Map<String,String> accountMap = new ConcurrentHashMap<>();
    /**
     * lagouserviceuser
     * /user/register/{email}/{password}/{code} true/false 注册接⼝，true成功，false失败
     * /user/isRegistered/{email} true/false 是否已注册，根据邮箱判断,true代表已经
     * 注册过，false代表尚未注册
     * /user/login/{email}/{password} 邮箱地址
     * 登录接⼝，验证⽤户名密码合法性，根据
     * ⽤户名和密码⽣成token，token存⼊数
     * 据库，并写⼊cookie中，登录成功返回邮
     * 箱地址，重定向到欢迎⻚
     * /user/info/{token} 邮箱地址 根据token查询⽤户登录邮箱接⼝
     */
    /**
     * 简易登陆
     * @param email
     * @param password
     * @return
     */
    @Override
    public String login (String email, String password) {
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
            return Constants.FAIL;
        }

        if (!password.equals(accountMap.get(email))) {
            return Constants.FAIL;
        }

        LagouToken lagouToken = new LagouToken();
        lagouToken.setEmail(email);
        lagouToken.setToken(UUID.randomUUID().toString().replace("-",""));
        lagouTokenDao.save(lagouToken);

        return lagouToken.getToken();
    }

    /**
     * 简易注册
     * @param email
     * @param password
     * @param code
     * @return
     */
    @Override
    @Transactional
    public String register(String email, String password, String code) {
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password) || StringUtils.isEmpty(code)) {
            return Constants.FAIL;
        }
        LagouAuthCode lagouAuthCodeParams = new LagouAuthCode();
        lagouAuthCodeParams.setEmail(email);
        lagouAuthCodeParams.setCode(code);

        Example example = Example.of(lagouAuthCodeParams);
        Sort sort = Sort.by(Sort.Direction.DESC,"createtime");

        List<LagouAuthCode> lagouAuthCodeList = lagouTokenDao.findAll(example, sort);
        if (CollectionUtils.isEmpty(lagouAuthCodeList)) {
            return Constants.FAIL;
        }

        LagouAuthCode lagouAuthCode = lagouAuthCodeList.get(0);
        if (!lagouAuthCode.getCode().equals(code)) {
            return Constants.FAIL;
        }

        // 偷懒模拟数据库保存账号
        accountMap.put(email,password);

        return Constants.SUCCESS;
    }
}
