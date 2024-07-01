package br.com.itau.desafioseguros.domain.entities;

import br.com.itau.desafioseguros.domain.exceptions.InsuranceProductBasePriceNullException;
import br.com.itau.desafioseguros.domain.exceptions.InsuranceProductCategoryNullException;
import br.com.itau.desafioseguros.domain.exceptions.InsuranceProductIdNullException;
import br.com.itau.desafioseguros.domain.exceptions.InsuranceProductNameNulException;
import br.com.itau.desafioseguros.domain.exceptions.InsuranceProductTariffedPriceNullException;
import br.com.itau.desafioseguros.domain.valueobjects.InsuranceProductId;
import br.com.itau.desafioseguros.domain.valueobjects.InsuranceProductCategory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;

class InsuranceProductTest {

    @Test
    void create_test() {
        Assertions.assertDoesNotThrow(() -> InsuranceProduct.create(new InsuranceProductId(UUID.randomUUID()),
                "teste",
                InsuranceProductCategory.VIDA,
                BigDecimal.valueOf(100),
                BigDecimal.valueOf(105)));
    }

    @Test
    void create_idNull_test() {
        assertThrows(InsuranceProductIdNullException.class, () -> InsuranceProduct.create(null,
                "teste",
                InsuranceProductCategory.VIDA,
                new BigDecimal(100),
                new BigDecimal(105)));
    }

    @Test
    void create_nameNull_test() {
        InsuranceProductId id = new InsuranceProductId(UUID.randomUUID());
        assertThrows(InsuranceProductNameNulException.class, () -> InsuranceProduct.create(id,
                null,
                InsuranceProductCategory.VIDA,
                BigDecimal.valueOf(100),
                BigDecimal.valueOf(105)));
    }

    @Test
    void create_nameEmpty_test() {
        InsuranceProductId id = new InsuranceProductId(UUID.randomUUID());
        assertThrows(InsuranceProductNameNulException.class, () -> InsuranceProduct.create(id,
                "",
                InsuranceProductCategory.VIDA,
                BigDecimal.valueOf(100),
                BigDecimal.valueOf(105)));
    }

    @Test
    void create_categoryNull_test() {
        InsuranceProductId id = new InsuranceProductId(UUID.randomUUID());
        assertThrows(InsuranceProductCategoryNullException.class, () -> InsuranceProduct.create(id,
                "teste",
                null,
                BigDecimal.valueOf(100),
                BigDecimal.valueOf(105)));
    }

    @Test
    void create_basePriceNull_test() {
        InsuranceProductId id = new InsuranceProductId(UUID.randomUUID());
        assertThrows(InsuranceProductBasePriceNullException.class, () -> InsuranceProduct.create(id,
                "teste",
                InsuranceProductCategory.VIDA,
                null,
                BigDecimal.valueOf(105)));
    }

    @Test
    void create_tariffedPriceNull_test() {
        InsuranceProductId id = new InsuranceProductId(UUID.randomUUID());
        assertThrows(InsuranceProductTariffedPriceNullException.class, () -> InsuranceProduct.create(id,
                "teste",
                InsuranceProductCategory.VIDA,
                BigDecimal.valueOf(100),
                null));
    }

}