package com.sxm.springboot.domain;

import com.sxm.springboot.validation.custom.multitype.DaysOfWeek;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

import static java.time.DayOfWeek.*;

public class DateRequest {

    @DaysOfWeek(days = {MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY})
    private LocalDate workingDay;

    @DaysOfWeek(days = {SATURDAY, SUNDAY})
    private Date weekendDay;

    public DateRequest(LocalDate workingDay, Date weekendDay) {
        Objects.requireNonNull(workingDay);
        Objects.requireNonNull(weekendDay);
        this.workingDay = workingDay;
        this.weekendDay = weekendDay;
    }

    public Date getWeekendDay() {
        return weekendDay;
    }

    public LocalDate getWorkingDay() {
        return workingDay;
    }

}
