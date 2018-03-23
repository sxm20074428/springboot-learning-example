package com.sxm.springboot.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sxm.springboot.controller.MailController;
import com.sxm.springboot.model.Email;
import com.sxm.springboot.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * 定义Receiver接收者
 *
 * @author 苏晓蒙
 * @version 0.1
 * @time 2017/9/18 15:26
 * @since 0.1
 */
@Component
public class Receiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    @Autowired
    private MailService mailService;

    private CountDownLatch countDownLatch;

    @Autowired
    public Receiver(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }
    @Autowired
    private ObjectMapper objectMapper;

    public void receiveTextMessage(String message) throws Exception {

        if (message == null) {
            LOGGER.info("接收email消息 <" + null + ">");
        } else {
            Email email = objectMapper.readValue(message, Email.class);
            LOGGER.info("接收email消息内容 <{}>", email);
            mailService.sendTextMail(email);
        }

        countDownLatch.countDown();
    }
}
