package org.planeswalker.service;

import lombok.extern.slf4j.Slf4j;
import org.planeswalker.pojo.dto.EmailDto;
import org.planeswalker.utils.JacksonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * 邮件相关服务层实现类
 * @author Planeswlker23
 * @date Created in 2020-02-07
 */
@Slf4j
@Service
public class MailService {

    @Value("${spring.mail.username}")
    private String sender;
    @Autowired
    private JavaMailSender mailSender;

    /**
     * 发送邮件
     * @param emailDto
     */
    public void sendEmail(EmailDto emailDto) {
        log.info("开始发送邮件 -> 传入参数: {}", JacksonUtil.toJson(emailDto));
        // 发送邮件，新建邮件对象
        SimpleMailMessage newMessage = new SimpleMailMessage();
        // 设置邮件主题
        newMessage.setSubject(emailDto.getTitle() + "，来自用户邮箱: " + emailDto.getSender());
        // 设置邮件内容
        newMessage.setText(emailDto.getContent());
        // 设置发件人
        newMessage.setFrom(sender);
        // 设置收件人
        newMessage.setTo(emailDto.getAccepter());
        mailSender.send(newMessage);
        log.info("邮件发送成功 -> 邮件实体类内容: {}", JacksonUtil.toJson(newMessage));
    }
}
