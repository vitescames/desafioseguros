package br.com.itau.desafioseguros.adapters.entrypoint.api.controller;

import br.com.itau.desafioseguros.application.command.AddInsuranceProductCommand;
import br.com.itau.desafioseguros.application.command.handlers.CommandHandler;
import br.com.itau.desafioseguros.application.command.responses.AddInsuranceProductCommandResponse;
import br.com.itau.desafioseguros.application.query.GetAllInsuranceProductsQuery;
import br.com.itau.desafioseguros.application.query.handlers.QueryHandler;
import br.com.itau.desafioseguros.application.query.responses.GetInsuranceProductQueryResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class InsuranceProductControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private InsuranceProductController insuranceProductController;

    @Mock
    private CommandHandler<AddInsuranceProductCommand, AddInsuranceProductCommandResponse> commandHandler;

    @Mock
    private QueryHandler<GetAllInsuranceProductsQuery, GetInsuranceProductQueryResponse> queryHandler;

    @Captor
    private ArgumentCaptor<AddInsuranceProductCommand> captorCommand;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(insuranceProductController)
                .build();
    }

    @Test
    void add_test() throws Exception {
        UUID uuid = UUID.fromString("d16a4f7d-fa2c-4ea1-ac9c-c2fce8088541");
        when(commandHandler.handle(captorCommand.capture())).thenReturn(new AddInsuranceProductCommandResponse(uuid,
                "teste",
                "teste",
                BigDecimal.valueOf(100),
                BigDecimal.valueOf(100)));

        this.mockMvc.perform(post("/insurance/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "nome": "Seguro de Vida Individual",
                            "categoria": "VIDA",
                            "preco_base": "100.00"
                        }"""))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json("""
                        {
                            "data":
                                {
                                    "id": "d16a4f7d-fa2c-4ea1-ac9c-c2fce8088541",
                                    "nome": "teste",
                                    "categoria": "teste",
                                    "preco_base": 100.0,
                                    "preco_tarifado": 100.0
                                }
                        }"""));

        AddInsuranceProductCommand command = captorCommand.getValue();

        assertEquals(new BigDecimal("100.00"), command.getBasePrice());
        assertEquals("VIDA", command.getCategory());
        assertEquals("Seguro de Vida Individual", command.getName());
    }

    @Test
    void get_test() throws Exception {
        UUID uuid = UUID.fromString("d16a4f7d-fa2c-4ea1-ac9c-c2fce8088541");
        when(queryHandler.handle(any(GetAllInsuranceProductsQuery.class))).thenReturn(List.of(new GetInsuranceProductQueryResponse(uuid,
                "teste",
                "teste",
                BigDecimal.valueOf(100),
                BigDecimal.valueOf(100))));

        this.mockMvc.perform(get("/insurance/products"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "data": [
                                {
                                    "id": "d16a4f7d-fa2c-4ea1-ac9c-c2fce8088541",
                                    "nome": "teste",
                                    "categoria": "teste",
                                    "preco_base": 100.0,
                                    "preco_tarifado": 100.0
                                }
                            ]
                        }"""));
    }

}