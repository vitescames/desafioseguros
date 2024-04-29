package br.com.itau.desafioseguros.domain.entities;

import br.com.itau.desafioseguros.domain.enums.InsuranceProductCategory;
import br.com.itau.desafioseguros.domain.exceptions.*;
import br.com.itau.desafioseguros.domain.valueobjects.InsuranceProductId;

public class InsuranceProduct {

    private final InsuranceProductId insuranceProductId;

    private final String name;

    private final InsuranceProductCategory category;

    private final Float basePrice;

    private final Float tariffedPrice;

    private InsuranceProduct(InsuranceProductId insuranceProductId,
                             String name,
                             InsuranceProductCategory category,
                             Float basePrice,
                             Float tariffedPrice) {
        this.insuranceProductId = insuranceProductId;
        this.name = name;
        this.category = category;
        this.basePrice = basePrice;
        this.tariffedPrice = tariffedPrice;
    }

    public static InsuranceProduct create(InsuranceProductId insuranceProductId,
                                          String name,
                                          InsuranceProductCategory category,
                                          Float basePrice,
                                          Float tariffedPrice) {
        if (insuranceProductId == null)
            throw new InsuranceProductIdNullException();

        if (name == null || name.isBlank())
            throw new InsuranceProductNameEmptyException();

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

    public Float getBasePrice() {
        return basePrice;
    }

    public Float getTariffedPrice() {
        return tariffedPrice;
    }
}
