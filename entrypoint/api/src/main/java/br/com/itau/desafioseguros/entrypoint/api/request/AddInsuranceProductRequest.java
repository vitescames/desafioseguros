package br.com.itau.desafioseguros.entrypoint.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class AddInsuranceProductRequest {

    @JsonProperty("nome")
    private final String name;

    @JsonProperty("categoria")
    private final String category;

    @JsonProperty("preco_base")
    private final BigDecimal basePrice;

    public AddInsuranceProductRequest(String name, String category, BigDecimal basePrice) {
        this.name = name;
        this.category = category;
        this.basePrice = basePrice;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }
}
