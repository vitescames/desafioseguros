package br.com.itau.desafioseguros.domain.repositories;

import br.com.itau.desafioseguros.domain.entities.InsuranceProduct;

public interface AddInsuranceProductRepository {
    InsuranceProduct add(InsuranceProduct insuranceProduct);
}
