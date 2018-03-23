package com.sxm.springboot.controller;

import com.sxm.springboot.common.BaseResponse;
import com.sxm.springboot.model.Email;
import com.sxm.springboot.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;

/**
 * 邮件服务
 *
 * @author 苏晓蒙
 * @version 0.1
 * @time 2017/9/18 12:22
 * @since 0.1
 */
@Controller
@RequestMapping("/mail")
public class MailController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailController.class);

    @Autowired
    MailService mailService;

    /**
     * 以文本格式发送邮件
     *
     * @param
     * @return
     * @author 苏晓蒙
     * @time 2017/9/18 12:46
     * @version 0.1
     * @since 0.1
     */
    @PostMapping("/text")
    public BaseResponse sendTextMail(Email mail) {

        try {
            LOGGER.info("发送邮件：{}", mail);
            mailService.sendTextMail(mail);
            LOGGER.info("简单邮件已经发送。");
            return BaseResponse.createSuccessResponse("简单邮已经发送");

        } catch (Exception e) {
            LOGGER.error("发送简单邮件时发生异常！", e);
        }
        return BaseResponse.createErrorResponse("邮件发送异常");

    }

    /**
     * 发送html邮件
     *
     * @param
     * @return
     * @author 苏晓蒙
     * @time 2017/9/18 12:46
     * @version 0.1
     * @since 0.1
     */
    @PostMapping("/html")
    public BaseResponse sendHtmlMail(Email mail) {

        try {
            LOGGER.info("发送邮件：{}", mail);
            mailService.sendHtmlMail(mail);
            LOGGER.info("html邮件发送成功。");
            return BaseResponse.createSuccessResponse("html邮件发送成功");

        } catch (Exception e) {
            LOGGER.error("发送html邮件时发生异常！", e);
        }
        return BaseResponse.createErrorResponse("发送html邮件时发生异常");

    }

    /**
     * 发送带附件的邮件
     *
     * @param file 文件
     * @return
     * @author 苏晓蒙
     * @time 2017/9/18 14:09
     * @version 0.1
     * @since 0.1
     */
    @PostMapping("/attachment")
    public BaseResponse sendAttachmentsMail(Email mail, File file) {

        try {
            LOGGER.info("发送邮件：{}", mail);
            mailService.sendAttachmentsMail(mail, file);
            LOGGER.info("带附件的邮件已经发送。");
            return BaseResponse.createSuccessResponse("带附件的邮件已经发送");
        } catch (Exception e) {
            LOGGER.error("发送带附件的邮件时发生异常！", e);
            return BaseResponse.createErrorResponse("发送带附件的邮件时发生异常");
        }
    }

    /**
     * 发送正文中有静态资源（图片）的邮件
     *
     * @param file 文件
     * @return
     * @author 苏晓蒙
     * @time 2017/9/18 14:12
     * @version 0.1
     * @since 0.1
     */
    @PostMapping("/inlineResource")
    public BaseResponse sendInlineResourceMail(Email mail, File file, String rscId) {

        try {
            LOGGER.info("发送邮件：{}", mail);
            mailService.sendInlineResourceMail(mail, file, rscId);
            LOGGER.info("嵌入静态资源的邮件已经发送。");
            return BaseResponse.createSuccessResponse("嵌入静态资源的邮件已经发送");

        } catch (Exception e) {
            LOGGER.error("发送嵌入静态资源的邮件时发生异常！", e);
            return BaseResponse.createErrorResponse("发送嵌入静态资源的邮件时发生异常");
        }
    }

    /**
     * 模版发送 freemarker
     *
     * @param mail
     * @throws Exception void
     */

    public BaseResponse sendFreemarkerMail(Email mail) {

        try {

            LOGGER.info("发送邮件：{}", mail);
            mailService.sendFreemarkerMail(mail);
            LOGGER.info("模版freemarker邮件已经发送。");
            return BaseResponse.createSuccessResponse("模版freemarker邮件已经发送");

        } catch (Exception e) {
            LOGGER.error("模版freemarker邮件发送发生异常！", e);
            return BaseResponse.createErrorResponse("模版freemarker邮件发送发生异常");
        }
    }

    /**
     * 模版发送 thymeleaf
     *
     * @param mail
     * @throws Exception void
     */
    public BaseResponse sendThymeleafMail(Email mail) {

        try {
            LOGGER.info("发送邮件：{}", mail);
            mailService.sendThymeleafMail(mail);
            LOGGER.info("模版thymeleaf邮件已经发送。");
            return BaseResponse.createSuccessResponse("模版thymeleaf邮件已经发送");
        } catch (Exception e) {
            LOGGER.error("模版thymeleaf邮件发送发生异常！", e);
            return BaseResponse.createErrorResponse("模版thymeleaf邮件发送发生异常");
        }
    }

    /**
     * Redis 队列
     *
     * @param mail
     * @throws Exception void
     */
    public BaseResponse sendBlockingQueue(Email mail) {
        try {
            LOGGER.info("发送邮件：{}", mail);
            mailService.sendQueueMail(mail);
            return BaseResponse.createSuccessResponse("RedisQueue邮件已经发送");
        } catch (Exception e) {
            LOGGER.error("RedisQueue邮件发送发生异常！", e);
            return BaseResponse.createErrorResponse("RedisQueue邮件发送发生异常");
        }
    }

    /**
     * Redis 队列
     *
     * @param mail
     * @throws Exception void
     */
    public BaseResponse sendRedisQueueMail(Email mail) {
        try {
            LOGGER.info("发送邮件：{}", mail);
            mailService.sendRedisQueueMail(mail);
            return BaseResponse.createSuccessResponse("RedisQueue邮件已经发送");
        } catch (Exception e) {
            LOGGER.error("RedisQueue邮件发送发生异常！", e);
            return BaseResponse.createErrorResponse("RedisQueue邮件发送发生异常");
        }
    }
}