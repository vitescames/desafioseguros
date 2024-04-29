package br.com.itau.desafioseguros.domain.valueobjects;

import br.com.itau.desafioseguros.domain.exceptions.InsuranceProductIdNullException;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InsuranceProductIdTest {

    @Test
    void create_test() {
        assertDoesNotThrow(() -> new InsuranceProductId(UUID.randomUUID()));
    }

    @Test
    void create_idNull_test() {
        assertThrows(InsuranceProductIdNullException.class, () -> new InsuranceProductId(null));
    }

}