package br.com.itau.desafioseguros.adapters.infrastructure.persistence;

import br.com.itau.desafioseguros.adapters.infrastructure.persistence.orm.InsuranceProductEntity;
import br.com.itau.desafioseguros.adapters.infrastructure.persistence.repositories.InsuranceProductRepository;
import br.com.itau.desafioseguros.domain.entities.InsuranceProduct;
import br.com.itau.desafioseguros.domain.enums.InsuranceProductCategory;
import br.com.itau.desafioseguros.domain.repositories.AddInsuranceProductRepository;
import br.com.itau.desafioseguros.domain.valueobjects.InsuranceProductId;
import br.com.itau.desafioseguros.domain.valueobjects.InsuranceProductName;
import org.springframework.stereotype.Component;

@Component
public class AddInsuranceProductRepositoryImpl implements AddInsuranceProductRepository {

    private final InsuranceProductRepository repository;

    public AddInsuranceProductRepositoryImpl(InsuranceProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public InsuranceProduct add(InsuranceProduct insuranceProduct) {
        InsuranceProductEntity entityToBeSaved = new InsuranceProductEntity(insuranceProduct.getInsuranceProductId().getId(),
                insuranceProduct.getInsuranceProductName().getName(),
                insuranceProduct.getInsuranceProductCategory().toString(),
                insuranceProduct.getInsuranceProductBasePrice(),
                insuranceProduct.getInsuranceProductTariffedPrice());

        InsuranceProductEntity entitySaved = repository.save(entityToBeSaved);

        return new InsuranceProduct(new InsuranceProductId(entitySaved.getId()),
                new InsuranceProductName(entitySaved.getName()),
                InsuranceProductCategory.valueOf(entitySaved.getCategory()),
                entitySaved.getBasePrice(),
                entitySaved.getTariffedPrice());
    }
}
