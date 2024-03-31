package br.com.itau.desafioseguros.application.command.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotNull;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EnumValidatorConstraint.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@NotNull
public @interface EnumValidator {

    Class<? extends Enum<?>> enumClass();

    String message() default "Deve ser válido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
