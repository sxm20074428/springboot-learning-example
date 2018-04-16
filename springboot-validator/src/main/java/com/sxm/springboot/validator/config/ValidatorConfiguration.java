package com.sxm.springboot.validator.config;

import com.sxm.springboot.dao.MenuRepository;
import com.sxm.springboot.validator.rule.*;
import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Arrays;

@Configuration
public class ValidatorConfiguration {

    @Autowired
    private MessageSource messageSource;

    @Bean
    @ConditionalOnClass(MessageSource.class)
    public LocalValidatorFactoryBean localValidatorFactoryBean() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setProviderClass(HibernateValidator.class);
        bean.setValidationMessageSource(messageSource);
        return bean;
    }

    @Bean
    OrderItemValidator orderItemValidator(MenuRepository menuRepository) {
        return new OrderItemValidatorComposite(Arrays.asList(
                new ItemMenuValidator(menuRepository),
                new ItemDescriptionValidator(),
                new ItemPriceValidator(),
                new ItemQuantityValidator()
        ));
    }
}
