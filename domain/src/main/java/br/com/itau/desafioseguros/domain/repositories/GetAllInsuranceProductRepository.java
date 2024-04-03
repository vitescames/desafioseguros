package br.com.itau.desafioseguros.domain.repositories;

import br.com.itau.desafioseguros.domain.entities.InsuranceProduct;

import java.util.List;

public interface GetAllInsuranceProductRepository {
    List<InsuranceProduct> get();
}
