package com.sxm.springboot.service;


import com.sxm.springboot.model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findById(Long id);
}
