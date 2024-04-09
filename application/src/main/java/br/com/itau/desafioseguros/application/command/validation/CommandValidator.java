package br.com.itau.desafioseguros.application.command.validation;

import br.com.itau.desafioseguros.application.command.Command;
import br.com.itau.desafioseguros.application.exceptions.CommandValidationException;
import br.com.itau.desafioseguros.application.exceptions.ValidationError;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface CommandValidator<T extends Command> {
    default void validate(T command) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        List<ValidationError> errors = new ArrayList<>();

        Set<ConstraintViolation<T>> violations = validator.validate(command);

        for (ConstraintViolation<T> violation : violations) {
            errors.add(new ValidationError(String.valueOf(violation.getPropertyPath()),
                    String.valueOf(violation.getInvalidValue()),
                    violation.getMessage()));
        }

        if (!errors.isEmpty()) {
            throw new CommandValidationException(errors);
        }
    }
}
