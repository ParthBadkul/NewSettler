package com.example.NewSettler.customValidators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = EnumValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)

public @interface ValidEnum {
    String message() default "Invalidvalue";

  public   Class<?>[] groups() default {};

   public Class<? extends Payload>[] payload() default {};

   public Class<? extends Enum<?>> enumClass();
}
