package com.example.app.common.annotation;

import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = EnumsValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidEnums {
    String message() default "올바른 코드값을 사용해주세요";
    Class[] groups() default {};
    Class[] payload() default {};
    Class<? extends java.lang.Enum<?>> enumClass();
}
