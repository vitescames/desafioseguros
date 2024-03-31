package br.com.itau.desafioseguros.application.command.handlers;

import br.com.itau.desafioseguros.application.command.AddInsuranceProductCommand;
import br.com.itau.desafioseguros.application.command.responses.AddInsuranceProductCommandResponse;
import br.com.itau.desafioseguros.application.command.validation.AddInsuranceProductCommandValidator;
import br.com.itau.desafioseguros.domain.annotations.LoggingMethod;
import br.com.itau.desafioseguros.domain.entities.InsuranceProduct;
import br.com.itau.desafioseguros.domain.enums.InsuranceProductCategory;
import br.com.itau.desafioseguros.domain.factories.TariffedPriceCalculatorStrategyFactory;
import br.com.itau.desafioseguros.domain.repositories.AddInsuranceProductRepository;
import br.com.itau.desafioseguros.domain.strategy.TariffedPriceCalculatorStrategy;
import br.com.itau.desafioseguros.domain.valueobjects.InsuranceProductId;
import br.com.itau.desafioseguros.domain.valueobjects.InsuranceProductName;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AddInsuranceProductCommandHandler implements CommandHandler<AddInsuranceProductCommand, AddInsuranceProductCommandResponse> {

    private final TariffedPriceCalculatorStrategyFactory strategyFactory;
    private final AddInsuranceProductCommandValidator validator;
    private final AddInsuranceProductRepository repository;

    public AddInsuranceProductCommandHandler(TariffedPriceCalculatorStrategyFactory strategyFactory,
                                             AddInsuranceProductCommandValidator validator,
                                             AddInsuranceProductRepository repository) {
        this.strategyFactory = strategyFactory;
        this.validator = validator;
        this.repository = repository;
    }

    @LoggingMethod
    @Override
    public AddInsuranceProductCommandResponse handle(AddInsuranceProductCommand command) {
        validator.validate(command);

        InsuranceProductCategory categoryEnum = InsuranceProductCategory.valueOf(command.getCategoria());
        TariffedPriceCalculatorStrategy strategy = strategyFactory.getStrategy(categoryEnum);

        float tariffedPrice = strategy.calculate(command.getPrecoBase());

        InsuranceProduct insuranceProduct = new InsuranceProduct(new InsuranceProductId(UUID.randomUUID()),
                new InsuranceProductName(command.getNome()),
                categoryEnum,
                command.getPrecoBase(),
                tariffedPrice);

        InsuranceProduct insuranceProductAdded = repository.add(insuranceProduct);

        return new AddInsuranceProductCommandResponse(insuranceProductAdded.getInsuranceProductId().getId(),
                insuranceProductAdded.getInsuranceProductName().getName(),
                insuranceProductAdded.getInsuranceProductCategory().toString(),
                insuranceProductAdded.getInsuranceProductBasePrice(),
                insuranceProductAdded.getInsuranceProductTariffedPrice());
    }
}
