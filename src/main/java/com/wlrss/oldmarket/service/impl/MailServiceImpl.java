package com.wlrss.oldmarket.service.impl;

import com.wlrss.oldmarket.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class MailServiceImpl  implements MailService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     *spring boot 提供的发邮件接口
     */
    @Autowired
    private JavaMailSender mailSender;

    //配置QQ邮箱
    @Value("${spring.mail.from}")
    private String from;

    /**
     * 简单文本
     * @param to  收件人
     * @param subject  主题
     * @param content  内容
     */
    @Override
    public void sendSimpleMail(String to, String subject, String content) {
        //创建SimpleMaliMessage对象
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        //邮件发送人
        mailMessage.setFrom(from);
        //邮件接收人
        mailMessage.setTo(to);
        //邮件主题
        mailMessage.setSubject(subject);
        //邮件内容
        mailMessage.setText(content);
        //发送邮件
        mailSender.send(mailMessage);
    }

    /**
     * html邮件
     * @param to  收件人
     * @param subject  主题
     * @param content  内容
     */
    @Override
    public void sendHtmlMail(String to, String subject, String content) {
        // 获取MimeMessage对象
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        try {
            mimeMessageHelper = new MimeMessageHelper(message,true);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(content,true);
            mailSender.send(message);
            //日志信息
            logger.info("邮件已经发送");
        } catch (MessagingException e) {
           logger.error("邮件发送异常");
        }
    }

    /**
     * 带附件的 邮件
     * @param to 收件人
     * @param subject  主题
     * @param content  内容
     * @param filePath  附件
     */
    @Override
    public void sendAttachmentsMail(String to, String subject, String content, String filePath) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content,true);

            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName,file);
            mailSender.send(message);
            logger.info("邮件已经发送");
        } catch (MessagingException e) {
            logger.error("邮件发送异常");
        }
    }
}
