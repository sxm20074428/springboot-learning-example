package com.sxm.springboot.service.user;

import com.sxm.springboot.dao.UserRepository;
import com.sxm.springboot.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author 苏晓蒙
 * @version 0.1
 * @time 2018/4/16 0016 上午 10:21
 * @since 0.1
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
