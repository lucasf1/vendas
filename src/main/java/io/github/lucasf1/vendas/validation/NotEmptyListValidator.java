package io.github.lucasf1.vendas.validation;

import java.util.List;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotEmptyListValidator implements ConstraintValidator<NotEmptyList, List> {

    @Override
    public boolean isValid(List list, ConstraintValidatorContext constraintValidatorContext) {

        return list != null && !list.isEmpty();
    }

    @Override
    public void initialize(NotEmptyList constraintAnnotation) {
        
    }

}
