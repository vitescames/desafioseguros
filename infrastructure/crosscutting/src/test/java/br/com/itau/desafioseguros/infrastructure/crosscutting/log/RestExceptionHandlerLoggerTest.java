package br.com.itau.desafioseguros.infrastructure.crosscutting.log;

import br.com.itau.desafioseguros.domain.exceptions.InsuranceProductIdNullException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class RestExceptionHandlerLoggerTest {

    @InjectMocks
    private RestExceptionHandlerLogger restExceptionHandlerLogger;

    @Test
    void beforeRestExceptionHandler_test() {
        assertDoesNotThrow(() -> restExceptionHandlerLogger.beforeRestExceptionHandler(new InsuranceProductIdNullException()));
    }

}