package br.com.itau.desafioseguros.adapters.infrastructure.persistence;

import br.com.itau.desafioseguros.adapters.infrastructure.persistence.orm.InsuranceProductEntity;
import br.com.itau.desafioseguros.adapters.infrastructure.persistence.repositories.InsuranceProductRepository;
import br.com.itau.desafioseguros.domain.entities.InsuranceProduct;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAllInsuranceProductRepositoryImplTest {

    @InjectMocks
    private GetAllInsuranceProductRepositoryImpl getAllInsuranceProductRepository;

    @Mock
    private InsuranceProductRepository insuranceProductRepository;

    @Test
    void get_test() {
        UUID uuid = UUID.fromString("d16a4f7d-fa2c-4ea1-ac9c-c2fce8088541");
        when(insuranceProductRepository.findAll()).thenReturn(List.of(new InsuranceProductEntity(uuid,
                "teste2",
                "AUTO",
                101f,
                106f)));

        List<InsuranceProduct> response = getAllInsuranceProductRepository.get();

        assertEquals("teste2", response.get(0).getName());
        assertEquals("d16a4f7d-fa2c-4ea1-ac9c-c2fce8088541", response.get(0).getInsuranceProductId().getId().toString());
        assertEquals("AUTO", response.get(0).getCategory().toString());
        assertEquals(101f, response.get(0).getBasePrice());
        assertEquals(106f, response.get(0).getTariffedPrice());
    }

}