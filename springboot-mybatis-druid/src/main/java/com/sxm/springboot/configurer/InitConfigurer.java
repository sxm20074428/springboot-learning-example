package com.sxm.springboot.configurer;

import com.sxm.springboot.dao.UserDao;
import com.sxm.springboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;

@Configuration
@Profile("default")
public class InitConfigurer{

    @Autowired
    private UserDao userDao;

    @PostConstruct
    public  void  init(){
        for (int i = 1; i <= 100; i++) {
            userDao.save(new User("TEST-NAME-" + i));
        }
    }
}
