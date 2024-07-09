package br.com.itau.desafioseguros.infrastructure.persistence;

import br.com.itau.desafioseguros.domain.entities.InsuranceProduct;
import br.com.itau.desafioseguros.domain.valueobjects.InsuranceProductCategory;
import br.com.itau.desafioseguros.domain.valueobjects.InsuranceProductId;
import br.com.itau.desafioseguros.infrastructure.persistence.orm.InsuranceProductEntity;
import br.com.itau.desafioseguros.infrastructure.persistence.repositories.InsuranceProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AddInsuranceProductRepositoryImplTest {

    @InjectMocks
    private AddInsuranceProductRepositoryImpl addInsuranceProductRepository;

    @Mock
    private InsuranceProductRepository insuranceProductRepository;

    @Captor
    private ArgumentCaptor<InsuranceProductEntity> insuranceProductEntityArgumentCaptor;

    @Test
    void add_test() {
        UUID uuid = UUID.fromString("d16a4f7d-fa2c-4ea1-ac9c-c2fce8088541");

        addInsuranceProductRepository.add(InsuranceProduct.create(new InsuranceProductId(uuid),
                "teste",
                InsuranceProductCategory.VIDA,
                BigDecimal.valueOf(100),
                BigDecimal.valueOf(105)));

        verify(insuranceProductRepository, only()).save(insuranceProductEntityArgumentCaptor.capture());

        InsuranceProductEntity entityToBeSaved = insuranceProductEntityArgumentCaptor.getValue();

        assertEquals("teste", entityToBeSaved.getName());
        assertEquals("d16a4f7d-fa2c-4ea1-ac9c-c2fce8088541", entityToBeSaved.getId().toString());
        assertEquals("VIDA", entityToBeSaved.getCategory());
        assertEquals(BigDecimal.valueOf(100), entityToBeSaved.getBasePrice());
        assertEquals(BigDecimal.valueOf(105), entityToBeSaved.getTariffedPrice());
    }

}