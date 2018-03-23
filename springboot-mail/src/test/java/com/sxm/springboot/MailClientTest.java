package com.sxm.springboot;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import com.sxm.springboot.model.Email;
import com.sxm.springboot.service.MailService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailClientTest {

    @Autowired
    private MailService mailService;

    private GreenMail smtpServer;

    @Before
    public void setUp() throws Exception {
        smtpServer = new GreenMail(new ServerSetup(25, null, "smtp"));
        smtpServer.start();
    }

    @Test
    public void sendFreemarkerMail() throws Exception {

        //创建邮件正文
        String content = "这是Freemarker模板邮件";
        Email email = new Email(new String[]{"sxm20074428@163.com"}, "主题：这是Freemarker模板邮件", content);
        email.setTemplateName("welcome");
        mailService.sendFreemarkerMail(email);
        assertReceivedMessageContains(content);
    }

    @Test
    public void sendThymeleafMail() throws Exception {

        String content = "Test message content";
        //创建邮件正文
        Email email = new Email(new String[]{"sxm20074428@163.com"}, "主题：这是Thymeleaf模板邮件", content);
        email.setTemplateName("mailTemplate");

        mailService.sendThymeleafMail(email);
        assertReceivedMessageContains(content);
    }

    private void assertReceivedMessageContains(String expected) throws IOException, MessagingException {
        MimeMessage[] receivedMessages = smtpServer.getReceivedMessages();
        assertEquals(1, receivedMessages.length);
        String content = (String) receivedMessages[0].getContent();
        System.out.println(content);
        assertTrue(content.contains(expected));
    }

    @After
    public void tearDown() throws Exception {
        smtpServer.stop();
    }

}