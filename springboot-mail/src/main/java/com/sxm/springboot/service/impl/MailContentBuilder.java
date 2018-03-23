package com.sxm.springboot.service.impl;

import com.sxm.springboot.model.Email;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class MailContentBuilder {

    /**
     * Thymeleaf;
     */
    @Autowired
    private TemplateEngine templateEngine;

    /**
     * freemarker
     */
    @Autowired
    public Configuration configuration;


    public String buildThymeleaf(Email mail) {
        Context context = new Context();
        context.setVariable("email", mail);
        return templateEngine.process(mail.getTemplateName(), context);
    }


    public String buildFreemarker(Email mail) throws IOException, TemplateException {

        Template template = configuration.getTemplate(mail.getTemplateName() + ".flt");
        return FreeMarkerTemplateUtils.processTemplateIntoString(template, mail);
    }

}