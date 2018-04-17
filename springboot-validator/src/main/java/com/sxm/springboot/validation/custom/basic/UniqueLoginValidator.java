package com.sxm.springboot.validation.custom.basic;

import com.sxm.springboot.dao.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueLoginValidator implements ConstraintValidator<UniqueLogin, String> {

    private UserRepository userRepository;

    public UniqueLoginValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void initialize(UniqueLogin constraint) {
    }

    public boolean isValid(String name, ConstraintValidatorContext context) {
        return name != null && !userRepository.findByName(name).isPresent();
    }

}
