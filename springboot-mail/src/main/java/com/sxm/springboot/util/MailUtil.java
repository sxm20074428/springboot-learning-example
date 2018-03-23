package com.sxm.springboot.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 异步发送
 *
 * @author 苏晓蒙
 * @version 0.1
 * @time 2018/3/21 0021 下午 16:33
 * @since 0.1
 */

@Component
public class MailUtil {

    /**
     * 执行者
     */
    @Autowired
    private  JavaMailSender javaMailSender;

    private  ExecutorService executorService = Executors.newFixedThreadPool(3);

    public void send(final SimpleMailMessage message) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                javaMailSender.send(message);
            }
        });
    }

    public void send(final MimeMessagePreparator messagePreparator) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                javaMailSender.send(messagePreparator);
            }
        });
    }
}
