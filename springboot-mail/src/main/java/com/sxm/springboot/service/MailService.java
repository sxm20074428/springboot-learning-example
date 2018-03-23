package com.sxm.springboot.service;

import com.sxm.springboot.model.Email;

import java.io.File;

public interface MailService {
    /**
     * 以文本格式发送邮件
     *
     * @param mail
     * @throws Exception void
     */
    public void sendTextMail(Email mail) throws Exception;

    /**
     * 发送html富文本邮件
     *
     * @param mail
     * @throws Exception void
     */
    public void sendHtmlMail(Email mail) throws Exception;

    /**
     * * 发送带附件的邮件
     *
     * @param mail
     * @param file
     * @return
     */
    void sendAttachmentsMail(Email mail, File file) throws Exception;

    /**
     * 发送正文中有静态资源（图片）的邮件
     *
     * @param mail
     * @param file
     * @param rscId
     * @return Exception void
     */
    void sendInlineResourceMail(Email mail, File file, String rscId) throws Exception;

    /**
     * 模版发送 freemarker
     *
     * @param mail
     * @throws Exception void
     */
    public void sendFreemarkerMail(Email mail) throws Exception;


    /**
     * 模版发送 thymeleaf
     *
     * @param mail
     * @throws Exception void
     */
    public void sendThymeleafMail(Email mail) throws Exception;

    /**
     * BlockingQueue 队列
     * 注意：LinkedBlockingQueue是进程内的队列，如果容器无情的挂掉以后，随之队列中的内容也便荡然无存。
     * @param mail
     * @throws Exception void
     */
    public void sendQueueMail(Email mail) throws Exception;

    /**
     * Redis 队列
     * 高可用
     *
     * @param mail
     * @throws Exception void
     */
    public void sendRedisQueueMail(Email mail) throws Exception;

}
