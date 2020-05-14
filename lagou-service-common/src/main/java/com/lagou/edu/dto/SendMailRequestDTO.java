package com.lagou.edu.dto;

import lombok.Data;

@Data
public class SendMailRequestDTO {
    private String toAddr;
    private String title;
    private String content;
}