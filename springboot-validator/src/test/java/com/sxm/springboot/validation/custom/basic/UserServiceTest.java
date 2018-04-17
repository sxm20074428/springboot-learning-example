package com.sxm.springboot.validation.custom.basic;

import com.sxm.springboot.dao.UserRepository;
import com.sxm.springboot.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Validator validator;

    @Test
    public void shouldValidateDuplicatedLogin() throws Exception {
        // given
        User predefinedUser = new User(100,"895219077@qq.com", "sxm","123456");
        userRepository.save(predefinedUser);
        // when
        User newUser = new User(101,"895219077@qq.com", "sxm","123456");

        Set<ConstraintViolation<User>> violations = validator.validate(newUser);
        // then
        assertEquals(1, violations.size());
    }

    private User createUser(int id,String userName,String name,String password) {
        User user = new User(id,userName,name,password);
        return user;
    }

}