package com.sxm.springboot.validation.custom.multitype;

import com.sxm.springboot.domain.DateRequest;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class DateRequestTest {

    private Validator validator;

    @Before
    public void setUp() throws Exception {

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    public void shouldMarkAllDatesInvalid() throws Exception {
        // given
        Date notWeekendDate = new GregorianCalendar(2017, Calendar.APRIL, 17).getTime();
        LocalDate notWorkingDate = LocalDate.of(2017, 4, 15);

        DateRequest request = new DateRequest(notWorkingDate, notWeekendDate);
        // when
        Set<ConstraintViolation<DateRequest>> violations = validator.validate(request);
        // then
        assertEquals(2, violations.size());
    }

}