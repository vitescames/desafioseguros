package br.com.itau.desafioseguros.domain.valueobjects;

import br.com.itau.desafioseguros.domain.exceptions.InsuranceProductNameEmptyException;

public class InsuranceProductName {

    private final String name;

    public InsuranceProductName(String name) {
        if (name == null || name.isBlank())
            throw new InsuranceProductNameEmptyException();

        this.name = name;
    }

    public String getName() {
        return name;
    }
}
