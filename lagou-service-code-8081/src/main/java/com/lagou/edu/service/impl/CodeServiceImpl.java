package com.lagou.edu.service.impl;

import com.lagou.edu.constant.Constants;
import com.lagou.edu.dao.CodeDao;
import com.lagou.edu.dto.SendMailRequestDTO;
import com.lagou.edu.pojo.LagouAuthCode;
import com.lagou.edu.service.CodeService;
import com.lagou.edu.service.MailService;
import com.lagou.edu.util.DateUtil;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class CodeServiceImpl implements CodeService {

    @Autowired
    private CodeDao codeDao;
    @Reference
    private MailService mailService;

    @Override
    public String generateCode(String email) {
        Date nowDate = new Date();
        String code = UUID.randomUUID().toString().replace("-","");
        LagouAuthCode lagouAuthCode = new LagouAuthCode();
        lagouAuthCode.setEmail(email);
        lagouAuthCode.setCode(code);
        lagouAuthCode.setCreatetime(nowDate);
        lagouAuthCode.setExpiretime(DateUtil.addMinutes(nowDate, 5));
        codeDao.save(lagouAuthCode);
        return code;
    }

    @Override
    @Transactional
    public String generateAndSendCode(String email) {
        String code = generateCode(email);
        // TODO 配置化
        SendMailRequestDTO sendMailRequestDTO = new SendMailRequestDTO();
        sendMailRequestDTO.setToAddr(email);
        sendMailRequestDTO.setTitle("LagouDemo 验证码");
        sendMailRequestDTO.setContent(code);
        mailService.sendTextMail(sendMailRequestDTO.getToAddr(),sendMailRequestDTO.getTitle(),sendMailRequestDTO.getContent());
        return Constants.SUCCESS;
    }

    @Override
    public Boolean verifyCode(String email, String code) {
        LagouAuthCode lagouAuthCode = new LagouAuthCode();
        lagouAuthCode.setEmail(email);
        lagouAuthCode.setCode(code);
        Example<LagouAuthCode> example = Example.of(lagouAuthCode);
        Optional<LagouAuthCode> lagouAuthCodeDB = codeDao.findOne(example);

        return lagouAuthCodeDB.isPresent() ? Boolean.TRUE : Boolean.FALSE;
    }

}
