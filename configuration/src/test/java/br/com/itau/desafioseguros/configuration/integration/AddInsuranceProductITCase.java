package br.com.itau.desafioseguros.configuration.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class AddInsuranceProductITCase {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void addInsuranceProduct_test() throws Exception {
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
                                    "nome": "Seguro de Vida Individual",
                                    "categoria": "VIDA",
                                    "preco_base": 100.00,
                                    "preco_tarifado": 103.20
                                }
                        }"""))
                .andExpect(jsonPath("$.data.id").exists());
    }

}
