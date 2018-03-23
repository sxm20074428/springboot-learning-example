package com.sxm.springboot.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sxm.springboot.model.Email;
import com.sxm.springboot.queue.MailQueue;
import com.sxm.springboot.service.MailService;
import com.sxm.springboot.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Service
public class MailServiceImpl implements MailService {

    /**
     * 发送者账户
     */
    @Value("${spring.mail.username}")
    public String userName;
    /**
     * redis订阅的频道
     */
    @Value("${spring.mail.channel}")
    public String channel;
    /**
     * 邮件内容建造者
     */
    @Autowired
    private MailContentBuilder mailContentBuilder;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MailUtil mailUtil;
    /**
     * 执行者
     */
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendTextMail(Email mail) throws Exception {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(userName);
        message.setTo(mail.getToEmails());
        message.setSubject(mail.getSubject());
        message.setText(mail.getContent());
        mailUtil.send(message);
    }

    @Override
    public void sendHtmlMail(Email mail) throws Exception {

        MimeMessagePreparator messagePreparator = mimeMessage -> {
            //true表示需要创建一个multipart message
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom(userName);
            mimeMessageHelper.setTo(mail.getToEmails());
            mimeMessageHelper.setSubject(mail.getSubject());
            mimeMessageHelper.setText(mail.getContent(), true);
        };
        mailUtil.send(messagePreparator);
    }


    @Override
    public void sendAttachmentsMail(Email mail, File file) throws Exception {

        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom(userName);
            mimeMessageHelper.setTo(mail.getToEmails());
            mimeMessageHelper.setSubject(mail.getSubject());
            mimeMessageHelper.setText(mail.getContent(), true);

            // 发送附件
            String fileName = file.getName();
            mimeMessageHelper.addAttachment(fileName, file);
        };
        mailUtil.send(messagePreparator);

    }

    @Override
    public void sendInlineResourceMail(Email mail, File file, String rscId) throws Exception {

        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom(userName);
            mimeMessageHelper.setTo(mail.getToEmails());
            mimeMessageHelper.setSubject(mail.getSubject());
            mimeMessageHelper.setText(mail.getContent(), true);
            // 发送图片
            mimeMessageHelper.addInline(rscId, file);
        };
        mailUtil.send(messagePreparator);


    }

    @Override
    public void sendFreemarkerMail(Email mail) throws Exception {

        MimeMessagePreparator messagePreparator = mimeMessage -> {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom(userName);
            mimeMessageHelper.setTo(mail.getToEmails());
            mimeMessageHelper.setSubject(mail.getSubject());

            Map<String, Object> hashMap = new HashMap<>();
            hashMap.put("content", mail.getContent());
            String content = mailContentBuilder.buildFreemarker(mail);
            mimeMessageHelper.setText(content, true);
        };
        mailUtil.send(messagePreparator);
    }

    @Override
    public void sendThymeleafMail(Email mail) throws Exception {

        MimeMessagePreparator messagePreparator = mimeMessage -> {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom(userName);
            mimeMessageHelper.setTo(mail.getToEmails());
            mimeMessageHelper.setSubject(mail.getSubject());

            String content = mailContentBuilder.buildThymeleaf(mail);
            mimeMessageHelper.setText(content, true);

        };

        mailUtil.send(messagePreparator);
    }

    @Override
    public void sendQueueMail(Email mail) throws Exception {
        MailQueue.getMailQueue().produce(mail);
    }

    @Override
    public void sendRedisQueueMail(Email mail) throws Exception {
        redisTemplate.convertAndSend(channel, objectMapper.writeValueAsString(mail));
    }
}
