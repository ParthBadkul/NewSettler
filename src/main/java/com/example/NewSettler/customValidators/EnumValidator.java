package com.example.NewSettler.customValidators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.util.Arrays;


public class EnumValidator implements ConstraintValidator<ValidEnum ,Enum<?>> {

    private Class<? extends Enum<?> > enumClass;

    @Override
    public void initialize(ValidEnum constraintAnnotation) {
       this.enumClass = constraintAnnotation.enumClass();
    }

    @Override
    public boolean isValid(Enum<?> s, ConstraintValidatorContext constraintValidatorContext) {
       if(s == null) return true;

       return Arrays.stream(enumClass.getEnumConstants()).anyMatch(e -> e.name().equalsIgnoreCase(s.name()));

    }
}
