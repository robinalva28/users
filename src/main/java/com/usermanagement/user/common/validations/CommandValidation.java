package com.usermanagement.user.common.validations;

import jakarta.validation.*;
import org.springframework.stereotype.Component;

import java.util.Set;


@Component
public abstract class CommandValidation<T> {

    private final Validator validator;

    protected CommandValidation() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    protected void validateSelf() {
        Set<ConstraintViolation<CommandValidation<T>>> violations = this.validator.validate(this, new Class[0]);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }


}
