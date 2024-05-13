package br.com.itau.desafioseguros.application.exceptions;

import br.com.itau.desafioseguros.application.command.Command;
import br.com.itau.desafioseguros.domain.exceptions.BaseException;
import jakarta.validation.ConstraintViolation;

import java.util.Set;

public class CommandValidationException extends BaseException {

    private final transient Set<ConstraintViolation<Command>> errorList;

    public CommandValidationException(Set<ConstraintViolation<Command>> errorList) {
        super("A validação do payload retornou erro(s)");
        this.errorList = errorList;
    }

    public Set<ConstraintViolation<Command>> getErrorList() {
        return errorList;
    }
}
