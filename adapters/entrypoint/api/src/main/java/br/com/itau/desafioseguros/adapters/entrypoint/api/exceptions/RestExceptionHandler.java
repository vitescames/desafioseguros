package br.com.itau.desafioseguros.adapters.entrypoint.api.exceptions;

import br.com.itau.desafioseguros.application.exceptions.CommandValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler({CommandValidationException.class})
    public ResponseEntity<RestErrorResponse> handleCommandExeption(CommandValidationException ex) {
        return new ResponseEntity<>(new RestErrorResponse(ex.getMessage(),
                ex.getErrorList().stream().map(validationError ->
                        new RestValidationError(validationError.getField(),
                                validationError.getInvalidValue(),
                                validationError.getErrorMessage()))
                        .collect(Collectors.toList())), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<RestErrorResponse> handleUnknownExeption(Exception ex) {
        return new ResponseEntity<>(new RestErrorResponse("Houve um erro desconhecido", null),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
