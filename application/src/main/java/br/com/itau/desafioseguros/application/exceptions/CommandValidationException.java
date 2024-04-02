package br.com.itau.desafioseguros.application.exceptions;

import br.com.itau.desafioseguros.domain.exceptions.BaseException;

import java.util.List;

public class CommandValidationException extends BaseException {

    private List<ValidationError> errorList;

    public CommandValidationException(List<ValidationError> errorList) {
        super("A validação do payload retornou erro(s)");
        this.errorList = errorList;
    }

    public List<ValidationError> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<ValidationError> errorList) {
        this.errorList = errorList;
    }
}
