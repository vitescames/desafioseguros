package br.com.itau.desafioseguros.entrypoint.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.UUID;

public class GetInsuranceProductResponse {

    private final UUID id;

    @JsonProperty("nome")
    private final String name;

    @JsonProperty("categoria")
    private final String category;

    @JsonProperty("preco_base")
    private final BigDecimal basePrice;

    @JsonProperty("preco_tarifado")
    private final BigDecimal tariffedPrice;

    public GetInsuranceProductResponse(UUID id, String name, String category, BigDecimal basePrice, BigDecimal tariffedPrice) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.basePrice = basePrice;
        this.tariffedPrice = tariffedPrice;
    }

    public UUID getId() {
        return id;
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

    public BigDecimal getTariffedPrice() {
        return tariffedPrice;
    }
}
