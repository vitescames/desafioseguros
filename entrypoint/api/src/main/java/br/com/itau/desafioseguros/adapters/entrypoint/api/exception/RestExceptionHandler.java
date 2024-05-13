package br.com.itau.desafioseguros.adapters.entrypoint.api.exception;

import br.com.itau.desafioseguros.application.exceptions.CommandValidationException;
import jakarta.validation.ConstraintViolation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler({CommandValidationException.class})
    public ResponseEntity<RestErrorResponse> handleCommandExeption(CommandValidationException ex) {
        return new ResponseEntity<>(new RestErrorResponse(ex.getMessage(),
                ex.getErrorList().stream().map(ConstraintViolation::getMessage)
                        .toList()), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<RestErrorResponse> handleHttpMessageNotReadableExceptionExeption(HttpMessageNotReadableException ex) {
        return new ResponseEntity<>(new RestErrorResponse("O formato ou a sintaxe da requisição está incorreta, " +
                "verifique e tente novamente", null),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<RestErrorResponse> handleUnknownExeption(Exception ex) {
        return new ResponseEntity<>(new RestErrorResponse("Houve um erro desconhecido", null),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
