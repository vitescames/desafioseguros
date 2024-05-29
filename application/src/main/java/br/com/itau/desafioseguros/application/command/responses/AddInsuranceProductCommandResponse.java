package br.com.itau.desafioseguros.application.command.responses;

import java.math.BigDecimal;
import java.util.UUID;

public class AddInsuranceProductCommandResponse implements CommandResponse {

    private final UUID id;

    private final String name;

    private final String category;

    private final BigDecimal basePrice;

    private final BigDecimal tariffedPrice;

    public AddInsuranceProductCommandResponse(UUID id, String name, String category, BigDecimal basePrice, BigDecimal tariffedPrice) {
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
