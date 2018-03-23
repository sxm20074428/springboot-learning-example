package com.sxm.springboot.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Email封装类
 *
 * @author 苏晓蒙
 * @version 0.1
 * @time 2017/9/20 15:39
 * @since 0.1
 */
public class Email implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 接收方邮件,必填
     */
    private String[] toEmails;
    /**
     * 主题,必填
     */
    private String subject;
    /**
     * 邮件内容,必填
     */
    private String content;
    /**
     * 模板，选填
     */
    private String templateName;
    /**
     * 自定义参数
     */
    private HashMap<String, Object> hashMap;

    public Email() {

    }

    public Email(String[] email, String subject, String content) {
        super();
        this.toEmails = email;
        this.subject = subject;
        this.content = content;
    }

    public String[] getToEmails() {
        return toEmails;
    }

    public void setToEmails(String[] toEmails) {
        this.toEmails = toEmails;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public HashMap<String, Object> getHashMap() {
        return hashMap;
    }

    public void setHashMap(HashMap<String, Object> hashMap) {
        this.hashMap = hashMap;
    }

    @Override
    public String toString() {
        return "Email{" + "toEmails=" + Arrays.toString(toEmails) + ", subject='" + subject + '\'' + ", content='" + content + '\'' + ", templateName='" + templateName + '\'' + '}';
    }
}
