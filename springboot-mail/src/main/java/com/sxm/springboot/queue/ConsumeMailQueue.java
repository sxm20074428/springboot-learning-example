package com.sxm.springboot.queue;

import com.sxm.springboot.model.Email;
import com.sxm.springboot.service.MailService;
import org.codehaus.groovy.runtime.powerassert.SourceText;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.sound.midi.Soundbank;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 消费队列
 *
 * @author 苏晓蒙
 * @version 0.1
 * @time 2018/3/22 0022 上午 10:31
 * @since 0.1
 */
@Component
public class ConsumeMailQueue {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumeMailQueue.class);

    @Autowired
    MailService mailService;

    @PostConstruct
    public void startThread() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);// 两个大小的固定线程池
        System.out.println("ConsumeMailQueue ****************************");
        executorService.submit(new PollMail(mailService));
        executorService.submit(new PollMail(mailService));
    }

    class PollMail implements Runnable {

        MailService mailService;

        public PollMail(MailService mailService) {
            this.mailService = mailService;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Email mail = MailQueue.getMailQueue().consume();
                    LOGGER.info("剩余邮件总数:{}", MailQueue.getMailQueue().size());
                    mailService.sendHtmlMail(mail);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @PreDestroy
    public void stopThread() {
        LOGGER.info("destroy");
    }
}
