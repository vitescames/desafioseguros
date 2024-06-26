package br.com.itau.desafioseguros.infrastructure.persistence;

import br.com.itau.desafioseguros.infrastructure.persistence.repositories.InsuranceProductRepository;
import br.com.itau.desafioseguros.domain.entities.InsuranceProduct;
import br.com.itau.desafioseguros.domain.repositories.GetAllInsuranceProductRepository;
import br.com.itau.desafioseguros.domain.valueobjects.InsuranceProductCategory;
import br.com.itau.desafioseguros.domain.valueobjects.InsuranceProductId;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllInsuranceProductRepositoryImpl implements GetAllInsuranceProductRepository {

    private final InsuranceProductRepository repository;

    public GetAllInsuranceProductRepositoryImpl(InsuranceProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<InsuranceProduct> get() {
        return repository.findAll().stream().map(insuranceProductEntity -> InsuranceProduct.create(new InsuranceProductId(insuranceProductEntity.getId()),
                insuranceProductEntity.getName(),
                InsuranceProductCategory.valueOf(insuranceProductEntity.getCategory()),
                insuranceProductEntity.getBasePrice(),
                insuranceProductEntity.getTariffedPrice())).toList();
    }
}
