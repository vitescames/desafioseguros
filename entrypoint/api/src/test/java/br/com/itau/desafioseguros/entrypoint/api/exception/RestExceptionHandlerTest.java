package br.com.itau.desafioseguros.entrypoint.api.exception;

import br.com.itau.desafioseguros.application.command.AddInsuranceProductCommand;
import br.com.itau.desafioseguros.application.exceptions.CommandValidationException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RestExceptionHandlerTest {

    private final RestExceptionHandler restExceptionHandler = new RestExceptionHandler();

    private final long beforeTimestamp = new Date().getTime();

    @Test
    void handleCommandExeption_test() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        RestErrorResponse response = restExceptionHandler
                .handleCommandExeption(new CommandValidationException(validator
                        .validate(new AddInsuranceProductCommand(null, null, null))));

        assertEquals(3, response.getErrors().size());
        assertNotNull(response.getMessage());
        assertTrue(response.getTimestamp() >= beforeTimestamp);
    }

    @Test
    void handleHttpMessageNotReadableExceptionExeption_test() {
        InvalidFormatException invalidFormatException = mock(InvalidFormatException.class);
        HttpInputMessage httpInputMessage = mock(HttpInputMessage.class);

        when(invalidFormatException.getPath()).thenReturn(List.of(new InvalidFormatException.Reference(null, "fieldName")));
        when(invalidFormatException.getTargetType()).thenReturn((Class) Integer.class);

        RestErrorResponse response = restExceptionHandler
                .handleHttpMessageNotReadableExceptionExeption(new HttpMessageNotReadableException("teste", invalidFormatException, httpInputMessage));

        assertEquals(1, response.getErrors().size());
        assertNotNull(response.getMessage());
        assertTrue(response.getTimestamp() >= beforeTimestamp);
    }

    @Test
    void handleHttpMessageNotReadableExceptionExeption_notInvalidFormatException_test() {
        CommandValidationException commandValidationException = mock(CommandValidationException.class);
        HttpInputMessage httpInputMessage = mock(HttpInputMessage.class);

        RestErrorResponse response = restExceptionHandler
                .handleHttpMessageNotReadableExceptionExeption(new HttpMessageNotReadableException("teste", commandValidationException, httpInputMessage));

        assertEquals(1, response.getErrors().size());
        assertNotNull(response.getMessage());
        assertTrue(response.getTimestamp() >= beforeTimestamp);
    }

    @Test
    void handleUnknownExeption_test() {
        RestErrorResponse response = restExceptionHandler.handleUnknownExeption();

        assertNull(response.getErrors());
        assertNotNull(response.getMessage());
        assertTrue(response.getTimestamp() >= beforeTimestamp);
    }

}