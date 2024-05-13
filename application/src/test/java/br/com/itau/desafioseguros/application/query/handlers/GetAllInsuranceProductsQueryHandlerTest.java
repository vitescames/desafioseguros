package br.com.itau.desafioseguros.application.query.handlers;

import br.com.itau.desafioseguros.application.query.GetAllInsuranceProductsQuery;
import br.com.itau.desafioseguros.application.query.responses.GetInsuranceProductQueryResponse;
import br.com.itau.desafioseguros.domain.entities.InsuranceProduct;
import br.com.itau.desafioseguros.domain.repositories.GetAllInsuranceProductRepository;
import br.com.itau.desafioseguros.domain.valueobjects.InsuranceProductCategory;
import br.com.itau.desafioseguros.domain.valueobjects.InsuranceProductId;
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
class GetAllInsuranceProductsQueryHandlerTest {

    @InjectMocks
    private GetAllInsuranceProductQueryHandler getAllInsuranceProductQueryHandler;

    @Mock
    private GetAllInsuranceProductRepository getAllInsuranceProductRepository;

    @Test
    void handle_test() {
        UUID uuid = UUID.fromString("d16a4f7d-fa2c-4ea1-ac9c-c2fce8088541");
        when(getAllInsuranceProductRepository.get()).thenReturn(List.of(InsuranceProduct.create(new InsuranceProductId(uuid),
                "teste",
                InsuranceProductCategory.VIDA,
                100f,
                105f)));

        List<GetInsuranceProductQueryResponse> response = getAllInsuranceProductQueryHandler.handle(new GetAllInsuranceProductsQuery());

        assertEquals("teste", response.get(0).getName());
        assertEquals("d16a4f7d-fa2c-4ea1-ac9c-c2fce8088541", response.get(0).getId().toString());
        assertEquals("VIDA", response.get(0).getCategory());
        assertEquals(100f, response.get(0).getBasePrice());
        assertEquals(105f, response.get(0).getTariffedPrice());
    }

}