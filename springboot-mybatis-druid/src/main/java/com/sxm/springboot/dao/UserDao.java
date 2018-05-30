package com.sxm.springboot.dao;


import com.sxm.springboot.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, Long> {}

