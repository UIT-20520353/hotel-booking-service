package com.service.hotel_booking.entities.validate;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class EachIntegerValidator implements ConstraintValidator<EachInteger, List<?>> {
    @Override
    public void initialize(EachInteger constraintAnnotation) {
    }

    @Override
    public boolean isValid(List<?> values, ConstraintValidatorContext context) {
        if (values == null) {
            return true;
        }
        for (Object value : values) {
            if (!(value instanceof Integer)) {
                return false;
            }
        }
        return true;
    }
}