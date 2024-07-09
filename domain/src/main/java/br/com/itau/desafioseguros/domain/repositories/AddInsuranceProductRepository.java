package br.com.itau.desafioseguros.domain.repositories;

import br.com.itau.desafioseguros.domain.entities.InsuranceProduct;

public interface AddInsuranceProductRepository {
    void add(InsuranceProduct insuranceProduct);
}
