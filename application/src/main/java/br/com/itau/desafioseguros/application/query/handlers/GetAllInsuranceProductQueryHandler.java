package br.com.itau.desafioseguros.application.query.handlers;

import br.com.itau.desafioseguros.application.query.GetAllInsuranceProductQuery;
import br.com.itau.desafioseguros.application.query.responses.GetInsuranceProductQueryResponse;
import br.com.itau.desafioseguros.domain.repositories.GetAllInsuranceProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllInsuranceProductQueryHandler implements QueryHandler<GetAllInsuranceProductQuery, GetInsuranceProductQueryResponse> {

    private final GetAllInsuranceProductRepository repository;

    public GetAllInsuranceProductQueryHandler(GetAllInsuranceProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<GetInsuranceProductQueryResponse> handle(GetAllInsuranceProductQuery query) {
        return repository.get().stream().map(insuranceProduct -> new GetInsuranceProductQueryResponse(insuranceProduct.getInsuranceProductId().getId(),
                insuranceProduct.getInsuranceProductName().getName(),
                insuranceProduct.getInsuranceProductCategory().toString(),
                insuranceProduct.getInsuranceProductBasePrice(),
                insuranceProduct.getInsuranceProductTariffedPrice())).toList();
    }
}
