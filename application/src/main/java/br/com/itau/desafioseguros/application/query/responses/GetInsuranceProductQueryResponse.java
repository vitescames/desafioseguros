package br.com.itau.desafioseguros.application.query.responses;

import java.util.UUID;

public class GetInsuranceProductQueryResponse implements QueryResponse {

    private final UUID id;

    private final String name;

    private final String category;

    private final Float basePrice;

    private final Float tariffedPrice;

    public GetInsuranceProductQueryResponse(UUID id, String name, String category, Float basePrice, Float tariffedPrice) {
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

    public Float getBasePrice() {
        return basePrice;
    }

    public Float getTariffedPrice() {
        return tariffedPrice;
    }

}
