package com.github.felipe.ifood.cadastro.common;

import javax.validation.ConstraintValidatorContext;

public interface DTO {

    default boolean isValid(ConstraintValidatorContext constraintViolationContext) {
        return true;
    }

}
