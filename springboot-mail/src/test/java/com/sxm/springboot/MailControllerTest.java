package com.sxm.springboot;

import com.sxm.springboot.controller.MailController;
import com.sxm.springboot.model.Email;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailControllerTest {

    @Autowired
    private MailController mailController;

    @Test
    public void testSimpleMail()  {
        Email email = new Email(new String[]{"sxm20074428@163.com"}, "test simple mail", " hello this is simple mail!");
        mailController.sendTextMail(email);
    }

    @Test
    public void testHtmlMail() {
        String content = "<html>\n" + "<body>\n" + "    <h3>hello world ! 这是一封html邮件!</h3>\n" + "</body>\n" + "</html>";
        Email email = new Email(new String[]{"sxm20074428@163.com"}, "test simple mail", content);
        mailController.sendHtmlMail(email);
    }

    @Test
    public void sendAttachmentsMail() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:static" + File.separator + "file" //
                + File.separator + "仓储服务.zip");
        Email email = new Email(new String[]{"sxm20074428@163.com"}, "主题：带附件的邮件", "有附件，请查收！");
        mailController.sendAttachmentsMail(email, file);
    }

    @Test
    public void sendInlineResourceMail() throws FileNotFoundException {
        String rscId = "neo006";
        String content = "<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
        Email email = new Email(new String[]{"sxm20074428@163.com"}, "主题：这是有图片的邮件", content);

        File file = ResourceUtils.getFile("classpath:static" + File.separator + "image" //
                + File.separator + "springcloud.png");

        mailController.sendInlineResourceMail(email, file, rscId);

    }

    @Test
    public void sendFreemarkerMail() {
        //创建邮件正文
        Email email = new Email(new String[]{"sxm20074428@163.com"}, "主题：这是Freemarker模板邮件", "这是Freemarker模板邮件");
        email.setTemplateName("welcome");
        mailController.sendFreemarkerMail(email);
    }

    @Test
    public void sendThymeleafMail() {
        //创建邮件正文
        Email email = new Email(new String[]{"895219077@qq.com"}, "主题：这是Thymeleaf模板邮件", "这是Thymeleaf模板邮件");
        email.setTemplateName("email");
        HashMap hashMap = new HashMap();
        hashMap.put("msg","我中奖了");
        email.setHashMap(hashMap);
        mailController.sendThymeleafMail(email);
    }

    @Test
    public void sendQueueMail() {
        //创建邮件正文
        Email email = new Email(new String[]{"sxm20074428@163.com"}, "主题：这是RedisQueue邮件", "这是RedisQueue邮件");
        try {
            for (int i = 0; i < 1; i++) {
                //测试用 小心被封
                mailController.sendBlockingQueue(email);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sendRedisQueueMail() {
        //创建邮件正文
        Email email = new Email(new String[]{"sxm20074428@163.com"}, "主题：这是RedisQueue邮件", "这是RedisQueue邮件");
        try {
            for (int i = 0; i < 1; i++) {
                //测试用 小心被封
                mailController.sendRedisQueueMail(email);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
