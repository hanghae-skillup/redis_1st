package com.example.app.common.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnumsValidator implements ConstraintValidator<ValidEnums, List<String>> {

    Set<String> enums;

    @Override
    public void initialize(ValidEnums constraintAnnotation) {
        enums = Stream.of(constraintAnnotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(List<String> values, ConstraintValidatorContext context) {
        if (values == null || values.isEmpty()) {
            return true;
        }

        boolean isValid = true;

        for (String value : values) {
            if (!enums.contains(value)) {
                isValid = false;
                break;
            }
        }

        return isValid;
    }
}
