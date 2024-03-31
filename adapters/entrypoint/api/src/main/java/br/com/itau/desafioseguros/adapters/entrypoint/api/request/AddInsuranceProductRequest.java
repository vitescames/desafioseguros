package br.com.itau.desafioseguros.adapters.entrypoint.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddInsuranceProductRequest {

    @JsonProperty("nome")
    private String name;

    @JsonProperty("categoria")
    private String category;

    @JsonProperty("preco_base")
    private Float basePrice;

    public AddInsuranceProductRequest(String name, String category, Float basePrice) {
        this.name = name;
        this.category = category;
        this.basePrice = basePrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Float getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Float basePrice) {
        this.basePrice = basePrice;
    }
}
