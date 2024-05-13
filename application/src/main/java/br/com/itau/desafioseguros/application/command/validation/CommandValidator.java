package br.com.itau.desafioseguros.application.command.validation;

import br.com.itau.desafioseguros.application.command.Command;
import br.com.itau.desafioseguros.application.exceptions.CommandValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;

public class CommandValidator {
    public void validate(Command command) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Command>> violations = validator.validate(command);

        if (!violations.isEmpty()) {
            throw new CommandValidationException(violations);
        }
    }
}
