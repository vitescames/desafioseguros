package br.com.itau.desafioseguros.application.command.validation;

import br.com.itau.desafioseguros.application.command.AddInsuranceProductCommand;
import br.com.itau.desafioseguros.application.exceptions.CommandValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AddInsuranceProductCommandValidatorTest {

    private final CommandValidator validator = new CommandValidator();

    @Test
    void validate_test() {
        assertDoesNotThrow(() -> validator.validate(new AddInsuranceProductCommand("teste", "VIDA", BigDecimal.valueOf(100))));
    }

    @Test
    void invalidate_test() {
        AddInsuranceProductCommand command = new AddInsuranceProductCommand(null, "VIDAf", null);
        CommandValidationException exception = assertThrows(CommandValidationException.class, () -> validator.validate(command));
        assertEquals(3, exception.getErrorList().size());
    }

}