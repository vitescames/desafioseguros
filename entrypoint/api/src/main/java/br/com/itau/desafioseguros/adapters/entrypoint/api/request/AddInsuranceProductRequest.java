package br.com.itau.desafioseguros.adapters.entrypoint.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddInsuranceProductRequest {

    @JsonProperty("nome")
    private final String name;

    @JsonProperty("categoria")
    private final String category;

    @JsonProperty("preco_base")
    private final Float basePrice;

    public AddInsuranceProductRequest(String name, String category, Float basePrice) {
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

    public Float getBasePrice() {
        return basePrice;
    }
}
