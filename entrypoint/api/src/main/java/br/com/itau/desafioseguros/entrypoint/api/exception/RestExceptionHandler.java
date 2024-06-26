package br.com.itau.desafioseguros.entrypoint.api.exception;

import br.com.itau.desafioseguros.application.exceptions.CommandValidationException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.validation.ConstraintViolation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

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
        String mensagem = "Não foi possível converter payload";

        if (ex.getCause() instanceof InvalidFormatException invalidFormatException) {
            String field = invalidFormatException.getPath().get(0).getFieldName();
            String targetType = invalidFormatException.getTargetType().getSimpleName();

            return new ResponseEntity<>(new RestErrorResponse(mensagem,
                    List.of(String.format("O campo %s deve ser do tipo %s", field, targetType))), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new RestErrorResponse(mensagem,
                List.of("O formato ou a sintaxe da requisição está incorreta")),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<RestErrorResponse> handleUnknownExeption() {
        return new ResponseEntity<>(new RestErrorResponse("Houve um erro desconhecido", null),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
