package com.sxm.springboot.dao;

import com.sxm.springboot.domain.User;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    private List<User> userList = new LinkedList<>();

    public void save(User user) {
        userList.add(user);
    }

    public Optional<User> findByName(String name) {
        return userList.stream()
                .filter(user -> user.getName().equals(name))
                .findFirst();
    }

}
