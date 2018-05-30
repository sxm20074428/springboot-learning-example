package com.sxm.springboot.service;


import com.sxm.springboot.dao.UserDao;
import com.sxm.springboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Optional<User> findById(Long id) {
        return userDao.findById(id);
    }

}
