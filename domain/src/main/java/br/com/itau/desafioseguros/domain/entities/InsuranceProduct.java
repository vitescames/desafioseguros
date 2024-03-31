package br.com.itau.desafioseguros.domain.entities;

import br.com.itau.desafioseguros.domain.enums.InsuranceProductCategory;
import br.com.itau.desafioseguros.domain.valueobjects.InsuranceProductId;
import br.com.itau.desafioseguros.domain.valueobjects.InsuranceProductName;

public class InsuranceProduct {

    private InsuranceProductId insuranceProductId;

    private InsuranceProductName insuranceProductName;

    private InsuranceProductCategory insuranceProductCategory;

    private Float insuranceProductBasePrice;

    private Float insuranceProductTariffedPrice;

    public InsuranceProduct(InsuranceProductId insuranceProductId,
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
