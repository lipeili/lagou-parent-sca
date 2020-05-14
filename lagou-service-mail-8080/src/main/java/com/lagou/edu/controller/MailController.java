package com.lagou.edu.controller;//package com.lagou.edu.controller;

import com.lagou.edu.constant.Constants;
import com.lagou.edu.dto.SendMailRequestDTO;
import com.lagou.edu.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private MailService mailService;

    /**
     * 邮件发送
     * @param sendMailRequestDTO
     * @return
     */
    @GetMapping("/send")
    public String send(SendMailRequestDTO sendMailRequestDTO) {
        mailService.sendTextMail(sendMailRequestDTO.getToAddr(), sendMailRequestDTO.getTitle(), sendMailRequestDTO.getContent());
        return Constants.SUCCESS;
    }
}
