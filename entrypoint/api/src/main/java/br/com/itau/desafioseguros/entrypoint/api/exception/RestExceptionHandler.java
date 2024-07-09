package br.com.itau.desafioseguros.entrypoint.api.exception;

import br.com.itau.desafioseguros.application.exceptions.CommandValidationException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.validation.ConstraintViolation;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler({CommandValidationException.class})
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public RestErrorResponse handleCommandExeption(CommandValidationException ex) {
        return new RestErrorResponse(ex.getMessage(),
                ex.getErrorList().stream().map(ConstraintViolation::getMessage).toList());
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public RestErrorResponse handleHttpMessageNotReadableExceptionExeption(HttpMessageNotReadableException ex) {
        String mensagem = "Não foi possível converter payload";

        if (ex.getCause() instanceof InvalidFormatException invalidFormatException) {
            String field = invalidFormatException.getPath().get(0).getFieldName();
            String targetType = invalidFormatException.getTargetType().getSimpleName();

            return new RestErrorResponse(mensagem,
                    List.of(String.format("O campo %s deve ser do tipo %s", field, targetType)));
        }

        return new RestErrorResponse(mensagem,
                List.of("O formato ou a sintaxe da requisição está incorreta"));
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public RestErrorResponse handleUnknownExeption() {
        return new RestErrorResponse("Houve um erro desconhecido", null);
    }

}
