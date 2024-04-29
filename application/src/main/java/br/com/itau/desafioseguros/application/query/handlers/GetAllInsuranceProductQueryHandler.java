package br.com.itau.desafioseguros.application.query.handlers;

import br.com.itau.desafioseguros.application.query.GetAllInsuranceProductQuery;
import br.com.itau.desafioseguros.application.query.responses.GetInsuranceProductQueryResponse;
import br.com.itau.desafioseguros.domain.repositories.GetAllInsuranceProductRepository;

import java.util.List;

public class GetAllInsuranceProductQueryHandler implements QueryHandler<GetAllInsuranceProductQuery, GetInsuranceProductQueryResponse> {

    private final GetAllInsuranceProductRepository repository;

    public GetAllInsuranceProductQueryHandler(GetAllInsuranceProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<GetInsuranceProductQueryResponse> handle(GetAllInsuranceProductQuery query) {
        return repository.get().stream().map(insuranceProduct -> new GetInsuranceProductQueryResponse(insuranceProduct.getInsuranceProductId().getId(),
                insuranceProduct.getName(),
                insuranceProduct.getCategory().toString(),
                insuranceProduct.getBasePrice(),
                insuranceProduct.getTariffedPrice())).toList();
    }
}
