package br.com.itau.desafioseguros.domain.valueobjects;

import br.com.itau.desafioseguros.domain.exceptions.EmptyInsuranceProductNameException;

public class InsuranceProductName {

    private String name;

    public InsuranceProductName(String name) {
        if (name == null || name.isBlank()) {
            throw new EmptyInsuranceProductNameException(name);
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
