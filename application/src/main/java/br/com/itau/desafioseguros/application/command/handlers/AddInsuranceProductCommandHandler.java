package br.com.itau.desafioseguros.application.command.handlers;

import br.com.itau.desafioseguros.application.command.AddInsuranceProductCommand;
import br.com.itau.desafioseguros.application.command.responses.AddInsuranceProductCommandResponse;
import br.com.itau.desafioseguros.application.command.validation.CommandValidator;
import br.com.itau.desafioseguros.application.event.EventPublisher;
import br.com.itau.desafioseguros.domain.entities.InsuranceProduct;
import br.com.itau.desafioseguros.domain.services.TariffedPriceCalculatorService;
import br.com.itau.desafioseguros.domain.valueobjects.InsuranceProductCategory;
import br.com.itau.desafioseguros.domain.valueobjects.InsuranceProductId;
import br.com.itau.desafioseguros.domain.repositories.AddInsuranceProductRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

public class AddInsuranceProductCommandHandler implements CommandHandler<AddInsuranceProductCommand, AddInsuranceProductCommandResponse> {

    private final TariffedPriceCalculatorService strategyFactory;
    private final CommandValidator validator;
    private final AddInsuranceProductRepository repository;
    private final EventPublisher eventPublisher;

    public AddInsuranceProductCommandHandler(TariffedPriceCalculatorService strategyFactory,
                                             CommandValidator validator,
                                             AddInsuranceProductRepository repository, EventPublisher eventPublisher) {
        this.strategyFactory = strategyFactory;
        this.validator = validator;
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public AddInsuranceProductCommandResponse handle(AddInsuranceProductCommand command) {
        validator.validate(command);

        InsuranceProductCategory categoryEnum = InsuranceProductCategory.valueOf(command.getCategory());

        BigDecimal tariffedPrice = strategyFactory.calculate(command.getBasePrice(), categoryEnum);

        InsuranceProduct insuranceProduct = InsuranceProduct.create(new InsuranceProductId(UUID.randomUUID()),
                command.getName(),
                categoryEnum,
                command.getBasePrice(),
                tariffedPrice);

        repository.add(insuranceProduct);

        insuranceProduct.getDomainEvents().forEach(eventPublisher::publish);

        return new AddInsuranceProductCommandResponse(insuranceProduct.getInsuranceProductId().getId(),
                insuranceProduct.getName(),
                insuranceProduct.getCategory().toString(),
                insuranceProduct.getBasePrice(),
                insuranceProduct.getTariffedPrice().setScale(2, RoundingMode.CEILING));
    }
}
