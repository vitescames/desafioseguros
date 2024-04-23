package br.com.itau.desafioseguros.domain.entities;

import br.com.itau.desafioseguros.domain.enums.InsuranceProductCategory;
import br.com.itau.desafioseguros.domain.exceptions.*;
import br.com.itau.desafioseguros.domain.valueobjects.InsuranceProductId;
import br.com.itau.desafioseguros.domain.valueobjects.InsuranceProductName;

public class InsuranceProduct {

    private final InsuranceProductId insuranceProductId;

    private final InsuranceProductName insuranceProductName;

    private final InsuranceProductCategory insuranceProductCategory;

    private final Float insuranceProductBasePrice;

    private final Float insuranceProductTariffedPrice;

    private InsuranceProduct(InsuranceProductId insuranceProductId,
                             InsuranceProductName insuranceProductName,
                             InsuranceProductCategory insuranceProductCategory,
                             Float insuranceProductBasePrice,
                             Float insuranceProductTariffedPrice) {
        this.insuranceProductId = insuranceProductId;
        this.insuranceProductName = insuranceProductName;
        this.insuranceProductCategory = insuranceProductCategory;
        this.insuranceProductBasePrice = insuranceProductBasePrice;
        this.insuranceProductTariffedPrice = insuranceProductTariffedPrice;
    }

    public static InsuranceProduct create(InsuranceProductId insuranceProductId,
                                          InsuranceProductName insuranceProductName,
                                          InsuranceProductCategory insuranceProductCategory,
                                          Float insuranceProductBasePrice,
                                          Float insuranceProductTariffedPrice) {
        if (insuranceProductId == null)
            throw new InsuranceProductIdNullException();

        if (insuranceProductName == null)
            throw new InsuranceProductNameEmptyException();

        if (insuranceProductCategory == null)
            throw new InsuranceProductCategoryNullException();

        if (insuranceProductBasePrice == null)
            throw new InsuranceProductBasePriceNullException();

        if (insuranceProductTariffedPrice == null)
            throw new InsuranceProductTariffedPriceNullException();

        return new InsuranceProduct(insuranceProductId,
                insuranceProductName,
                insuranceProductCategory,
                insuranceProductBasePrice,
                insuranceProductTariffedPrice);
    }

    public InsuranceProductId getInsuranceProductId() {
        return insuranceProductId;
    }

    public InsuranceProductName getInsuranceProductName() {
        return insuranceProductName;
    }

    public InsuranceProductCategory getInsuranceProductCategory() {
        return insuranceProductCategory;
    }

    public Float getInsuranceProductBasePrice() {
        return insuranceProductBasePrice;
    }

    public Float getInsuranceProductTariffedPrice() {
        return insuranceProductTariffedPrice;
    }
}
