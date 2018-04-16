package com.sxm.springboot.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


@Repository
public class MenuRepository {

    public static final Logger LOGGER = LoggerFactory.getLogger(MenuRepository.class);

    public boolean menuExists(String menuId) {
        LOGGER.info("Menu id {}.", menuId);
        return true;
    }
}
