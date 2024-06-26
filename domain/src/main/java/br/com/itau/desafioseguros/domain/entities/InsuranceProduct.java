package br.com.itau.desafioseguros.domain.entities;

import br.com.itau.desafioseguros.domain.events.DomainEvent;
import br.com.itau.desafioseguros.domain.exceptions.*;
import br.com.itau.desafioseguros.domain.valueobjects.InsuranceProductCategory;
import br.com.itau.desafioseguros.domain.valueobjects.InsuranceProductId;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class InsuranceProduct {

    private final InsuranceProductId insuranceProductId;

    private final String name;

    private final InsuranceProductCategory category;

    private final BigDecimal basePrice;

    private final BigDecimal tariffedPrice;

    private final List<DomainEvent> domainEvents = new ArrayList<>();

    private InsuranceProduct(InsuranceProductId insuranceProductId,
                             String name,
                             InsuranceProductCategory category,
                             BigDecimal basePrice,
                             BigDecimal tariffedPrice) {
        this.insuranceProductId = insuranceProductId;
        this.name = name;
        this.category = category;
        this.basePrice = basePrice;
        this.tariffedPrice = tariffedPrice;
    }

    public static InsuranceProduct create(InsuranceProductId insuranceProductId,
                                          String name,
                                          InsuranceProductCategory category,
                                          BigDecimal basePrice,
                                          BigDecimal tariffedPrice) {
        if (insuranceProductId == null)
            throw new InsuranceProductIdNullException();

        if (name == null || name.isBlank())
            throw new InsuranceProductNameNulException();

        if (category == null)
            throw new InsuranceProductCategoryNullException();

        if (basePrice == null)
            throw new InsuranceProductBasePriceNullException();

        if (tariffedPrice == null)
            throw new InsuranceProductTariffedPriceNullException();

        return new InsuranceProduct(insuranceProductId,
                name,
                category,
                basePrice,
                tariffedPrice);
    }

    public InsuranceProductId getInsuranceProductId() {
        return insuranceProductId;
    }

    public String getName() {
        return name;
    }

    public InsuranceProductCategory getCategory() {
        return category;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public BigDecimal getTariffedPrice() {
        return tariffedPrice;
    }

    public List<DomainEvent> getDomainEvents() {
        return domainEvents;
    }

    public void registerEvent(DomainEvent domainEvent) {
        domainEvents.add(domainEvent);
    }
}
