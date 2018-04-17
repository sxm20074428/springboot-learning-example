package com.sxm.springboot.validation.custom.multitype;

import java.time.LocalDate;

public class DaysOfWeekLocalDateValidator extends DaysOfWeekValidator<LocalDate> {

    @Override
    protected LocalDate toLocalDate(LocalDate value) {
        return value;
    }

}
