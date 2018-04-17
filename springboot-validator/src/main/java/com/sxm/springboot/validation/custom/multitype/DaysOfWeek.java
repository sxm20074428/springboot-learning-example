package com.sxm.springboot.validation.custom.multitype;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.DayOfWeek;

/**
 * @author 苏晓蒙
 * @version 0.1
 * @time 2018/4/17 0017 下午 14:03
 * @since 0.1
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {DaysOfWeekLocalDateValidator.class, DaysOfWeekDateValidator.class})
public @interface DaysOfWeek {

    String message() default "{com.sxm.springboot.validation.custom.multiType.DaysOfWeek.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    DayOfWeek[] days() default {DayOfWeek.MONDAY};

}
