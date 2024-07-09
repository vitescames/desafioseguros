package br.com.itau.desafioseguros.infrastructure.persistence;

import br.com.itau.desafioseguros.infrastructure.persistence.orm.InsuranceProductEntity;
import br.com.itau.desafioseguros.infrastructure.persistence.repositories.InsuranceProductRepository;
import br.com.itau.desafioseguros.domain.entities.InsuranceProduct;
import br.com.itau.desafioseguros.domain.repositories.AddInsuranceProductRepository;
import org.springframework.stereotype.Component;

@Component
public class AddInsuranceProductRepositoryImpl implements AddInsuranceProductRepository {

    private final InsuranceProductRepository repository;

    public AddInsuranceProductRepositoryImpl(InsuranceProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public void add(InsuranceProduct insuranceProduct) {
        InsuranceProductEntity entityToBeSaved = new InsuranceProductEntity(insuranceProduct.getInsuranceProductId().getId(),
                insuranceProduct.getName(),
                insuranceProduct.getCategory().toString(),
                insuranceProduct.getBasePrice(),
                insuranceProduct.getTariffedPrice());

        repository.save(entityToBeSaved);
    }
}
