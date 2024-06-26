package br.com.itau.desafioseguros.infrastructure.crosscutting.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddInsuranceProductCommandHandlerLoggerTest {

    @InjectMocks
    private AddInsuranceProductCommandHandlerLogger addInsuranceProductCommandHandlerLogger;

    @Test
    void logAroundAddInsuranceProductCommandHandler_test() {
        ProceedingJoinPoint joinPoint = mock(ProceedingJoinPoint.class);
        MethodSignature signature = mock(MethodSignature.class);

        when(joinPoint.getSignature()).thenReturn(signature);
        when(signature.getName()).thenReturn("teste");

        assertDoesNotThrow(() -> addInsuranceProductCommandHandlerLogger.logAroundAddInsuranceProductCommandHandler(joinPoint));
    }

}