package br.com.itau.desafioseguros.application.command.handlers;

import br.com.itau.desafioseguros.application.command.AddInsuranceProductCommand;
import br.com.itau.desafioseguros.application.command.responses.AddInsuranceProductCommandResponse;
import br.com.itau.desafioseguros.application.command.validation.CommandValidator;
import br.com.itau.desafioseguros.domain.entities.InsuranceProduct;
import br.com.itau.desafioseguros.domain.repositories.AddInsuranceProductRepository;
import br.com.itau.desafioseguros.domain.shared.annotations.LoggingMethod;
import br.com.itau.desafioseguros.domain.strategy.TariffedPriceCalculatorStrategy;
import br.com.itau.desafioseguros.domain.strategy.TariffedPriceCalculatorStrategyFactory;
import br.com.itau.desafioseguros.domain.valueobjects.InsuranceProductCategory;
import br.com.itau.desafioseguros.domain.valueobjects.InsuranceProductId;

import java.util.UUID;

public class AddInsuranceProductCommandHandler implements CommandHandler<AddInsuranceProductCommand, AddInsuranceProductCommandResponse> {

    private final TariffedPriceCalculatorStrategyFactory strategyFactory;
    private final CommandValidator validator;
    private final AddInsuranceProductRepository repository;

    public AddInsuranceProductCommandHandler(TariffedPriceCalculatorStrategyFactory strategyFactory,
                                             CommandValidator validator,
                                             AddInsuranceProductRepository repository) {
        this.strategyFactory = strategyFactory;
        this.validator = validator;
        this.repository = repository;
    }

    @LoggingMethod
    @Override
    public AddInsuranceProductCommandResponse handle(AddInsuranceProductCommand command) {
        validator.validate(command);

        InsuranceProductCategory categoryEnum = InsuranceProductCategory.valueOf(command.getCategory());
        TariffedPriceCalculatorStrategy strategy = strategyFactory.getStrategy(categoryEnum);

        float tariffedPrice = strategy.calculate(command.getBasePrice());

        InsuranceProduct insuranceProduct = InsuranceProduct.create(new InsuranceProductId(UUID.randomUUID()),
                command.getName(),
                categoryEnum,
                command.getBasePrice(),
                tariffedPrice);

        InsuranceProduct insuranceProductAdded = repository.add(insuranceProduct);

        return new AddInsuranceProductCommandResponse(insuranceProductAdded.getInsuranceProductId().getId(),
                insuranceProductAdded.getName(),
                insuranceProductAdded.getCategory().toString(),
                insuranceProductAdded.getBasePrice(),
                insuranceProductAdded.getTariffedPrice());
    }
}
