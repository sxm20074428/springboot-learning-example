package com.sxm.springboot.validation.custom.crossfield.generic;

import com.sxm.springboot.domain.UserSignUpForm;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class UserSignUpFormTest {

    private Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    public void shouldMarkPasswordsAsInvalid() {
        //given
        UserSignUpForm form = new UserSignUpForm("daniel", "pass", "differentPass");
        //when
        Set<ConstraintViolation<UserSignUpForm>> violations = validator.validate(form);
        //then
        assertEquals(1, violations.size());
    }

}