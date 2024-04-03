package br.com.itau.desafioseguros.adapters.entrypoint.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class GetInsuranceProductResponse {

    private UUID id;

    @JsonProperty("nome")
    private String name;

    @JsonProperty("categoria")
    private String category;

    @JsonProperty("preco_base")
    private Float basePrice;

    @JsonProperty("preco_tarifado")
    private Float tariffedPrice;

    public GetInsuranceProductResponse(UUID id, String name, String category, Float basePrice, Float tariffedPrice) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.basePrice = basePrice;
        this.tariffedPrice = tariffedPrice;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public Float getTariffedPrice() {
        return tariffedPrice;
    }

    public void setTariffedPrice(Float tariffedPrice) {
        this.tariffedPrice = tariffedPrice;
    }
}
