package br.com.itau.desafioseguros.application.query.handlers;

import br.com.itau.desafioseguros.application.query.GetAllInsuranceProductsQuery;
import br.com.itau.desafioseguros.application.query.responses.GetInsuranceProductQueryResponse;
import br.com.itau.desafioseguros.domain.repositories.GetAllInsuranceProductRepository;

import java.util.List;

public class GetAllInsuranceProductQueryHandler implements QueryHandler<GetAllInsuranceProductsQuery, GetInsuranceProductQueryResponse> {

    private final GetAllInsuranceProductRepository repository;

    public GetAllInsuranceProductQueryHandler(GetAllInsuranceProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<GetInsuranceProductQueryResponse> handle(GetAllInsuranceProductsQuery query) {
        return repository.get().stream().map(insuranceProduct -> new GetInsuranceProductQueryResponse(insuranceProduct.getInsuranceProductId().getId(),
                insuranceProduct.getName(),
                insuranceProduct.getCategory().toString(),
                insuranceProduct.getBasePrice(),
                insuranceProduct.getTariffedPrice())).toList();
    }
}
