package br.com.itau.desafioseguros.application.query.responses;

import java.util.UUID;

public class GetInsuranceProductQueryResponse implements QueryResponse {

    private UUID id;

    private String name;

    private String category;

    private Float basePrice;

    private Float tariffedPrice;

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
