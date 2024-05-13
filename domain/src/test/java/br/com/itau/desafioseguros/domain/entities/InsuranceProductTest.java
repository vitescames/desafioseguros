package br.com.itau.desafioseguros.domain.entities;

import br.com.itau.desafioseguros.domain.exceptions.*;
import br.com.itau.desafioseguros.domain.valueobjects.InsuranceProductCategory;
import br.com.itau.desafioseguros.domain.valueobjects.InsuranceProductId;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InsuranceProductTest {

    @Test
    void create_test() {
        assertDoesNotThrow(() -> InsuranceProduct.create(new InsuranceProductId(UUID.randomUUID()),
                "teste",
                InsuranceProductCategory.VIDA,
                100f,
                105f));
    }

    @Test
    void create_idNull_test() {
        assertThrows(InsuranceProductIdNullException.class, () -> InsuranceProduct.create(null,
                "teste",
                InsuranceProductCategory.VIDA,
                100f,
                105f));
    }

    @Test
    void create_nameNull_test() {
        InsuranceProductId id = new InsuranceProductId(UUID.randomUUID());
        assertThrows(InsuranceProductNameNulException.class, () -> InsuranceProduct.create(id,
                null,
                InsuranceProductCategory.VIDA,
                100f,
                105f));
    }

    @Test
    void create_nameEmpty_test() {
        InsuranceProductId id = new InsuranceProductId(UUID.randomUUID());
        assertThrows(InsuranceProductNameNulException.class, () -> InsuranceProduct.create(id,
                "",
                InsuranceProductCategory.VIDA,
                100f,
                105f));
    }

    @Test
    void create_categoryNull_test() {
        InsuranceProductId id = new InsuranceProductId(UUID.randomUUID());
        assertThrows(InsuranceProductCategoryNullException.class, () -> InsuranceProduct.create(id,
                "teste",
                null,
                100f,
                105f));
    }

    @Test
    void create_basePriceNull_test() {
        InsuranceProductId id = new InsuranceProductId(UUID.randomUUID());
        assertThrows(InsuranceProductBasePriceNullException.class, () -> InsuranceProduct.create(id,
                "teste",
                InsuranceProductCategory.VIDA,
                null,
                105f));
    }

    @Test
    void create_tariffedPriceNull_test() {
        InsuranceProductId id = new InsuranceProductId(UUID.randomUUID());
        assertThrows(InsuranceProductTariffedPriceNullException.class, () -> InsuranceProduct.create(id,
                "teste",
                InsuranceProductCategory.VIDA,
                100f,
                null));
    }

}