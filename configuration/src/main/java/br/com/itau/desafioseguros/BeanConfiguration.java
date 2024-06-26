package br.com.itau.desafioseguros;

import br.com.itau.desafioseguros.application.command.AddInsuranceProductCommand;
import br.com.itau.desafioseguros.application.command.handler.AddInsuranceProductCommandHandler;
import br.com.itau.desafioseguros.application.command.handler.CommandHandler;
import br.com.itau.desafioseguros.application.command.responses.AddInsuranceProductCommandResponse;
import br.com.itau.desafioseguros.application.command.validation.CommandValidator;
import br.com.itau.desafioseguros.application.event.handler.InsuranceProductCreatedEventHandler;
import br.com.itau.desafioseguros.application.event.publisher.EventBus;
import br.com.itau.desafioseguros.application.event.publisher.EventBusImpl;
import br.com.itau.desafioseguros.application.query.GetAllInsuranceProductsQuery;
import br.com.itau.desafioseguros.application.query.handlers.GetAllInsuranceProductQueryHandler;
import br.com.itau.desafioseguros.application.query.handlers.QueryHandler;
import br.com.itau.desafioseguros.application.query.responses.GetInsuranceProductQueryResponse;
import br.com.itau.desafioseguros.domain.events.InsuranceProductCreated;
import br.com.itau.desafioseguros.domain.repositories.AddInsuranceProductRepository;
import br.com.itau.desafioseguros.domain.repositories.GetAllInsuranceProductRepository;
import br.com.itau.desafioseguros.domain.services.strategy.AutoTariffedPriceCalculatorStrategy;
import br.com.itau.desafioseguros.domain.services.strategy.PatrimonialTariffedPriceCalculatorStrategy;
import br.com.itau.desafioseguros.domain.services.strategy.ResidTariffedPriceCalculatorStrategy;
import br.com.itau.desafioseguros.domain.services.strategy.TariffedPriceCalculatorStrategy;
import br.com.itau.desafioseguros.domain.services.strategy.TariffedPriceCalculatorStrategyFactory;
import br.com.itau.desafioseguros.domain.services.strategy.ViagemTariffedPriceCalculatorStrategy;
import br.com.itau.desafioseguros.domain.services.strategy.VidaTariffedPriceCalculatorStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class BeanConfiguration {

    @Bean
    public TariffedPriceCalculatorStrategy vidaTariffedPriceCalculatorStrategy() {
        return new VidaTariffedPriceCalculatorStrategy();
    }

    @Bean
    public TariffedPriceCalculatorStrategy autoTariffedPriceCalculatorStrategy() {
        return new AutoTariffedPriceCalculatorStrategy();
    }

    @Bean
    public TariffedPriceCalculatorStrategy viagemTariffedPriceCalculatorStrategy() {
        return new ViagemTariffedPriceCalculatorStrategy();
    }

    @Bean
    public TariffedPriceCalculatorStrategy residTariffedPriceCalculatorStrategy() {
        return new ResidTariffedPriceCalculatorStrategy();
    }

    @Bean
    public TariffedPriceCalculatorStrategy patrimonialTariffedPriceCalculatorStrategy() {
        return new PatrimonialTariffedPriceCalculatorStrategy();
    }

    @Bean
    public TariffedPriceCalculatorStrategyFactory tariffedPriceCalculatorStrategyFactory(Set<TariffedPriceCalculatorStrategy> strategies) {
        return new TariffedPriceCalculatorStrategyFactory(strategies);
    }

    @Bean
    public CommandValidator commandValidator() {
        return new CommandValidator();
    }

    @Bean
    public CommandHandler<AddInsuranceProductCommand, AddInsuranceProductCommandResponse> addInsuranceProductCommandHandler(TariffedPriceCalculatorStrategyFactory strategyFactory,
                                                                                                                            CommandValidator validator,
                                                                                                                            AddInsuranceProductRepository repository,
                                                                                                                            EventBus eventBus) {
        return new AddInsuranceProductCommandHandler(strategyFactory, validator, repository, eventBus);
    }

    @Bean
    public QueryHandler<GetAllInsuranceProductsQuery, GetInsuranceProductQueryResponse> getAllInsuranceProductQueryHandler(GetAllInsuranceProductRepository getAllInsuranceProductRepository) {
        return new GetAllInsuranceProductQueryHandler(getAllInsuranceProductRepository);
    }

    @Bean
    public EventBus eventBus() {
        EventBus eventBusImpl = new EventBusImpl();
        eventBusImpl.registerHandler(InsuranceProductCreated.class, new InsuranceProductCreatedEventHandler());
        return eventBusImpl;
    }
}
