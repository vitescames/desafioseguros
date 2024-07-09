package br.com.itau.desafioseguros.configuration;

import br.com.itau.desafioseguros.application.command.AddInsuranceProductCommand;
import br.com.itau.desafioseguros.application.command.handlers.AddInsuranceProductCommandHandler;
import br.com.itau.desafioseguros.application.command.handlers.CommandHandler;
import br.com.itau.desafioseguros.application.command.responses.AddInsuranceProductCommandResponse;
import br.com.itau.desafioseguros.application.command.validation.CommandValidator;
import br.com.itau.desafioseguros.application.event.handlers.DomainEventHandler;
import br.com.itau.desafioseguros.application.event.handlers.InsuranceProductCreatedEventHandler;
import br.com.itau.desafioseguros.application.event.EventPublisher;
import br.com.itau.desafioseguros.application.query.GetAllInsuranceProductsQuery;
import br.com.itau.desafioseguros.application.query.handlers.GetAllInsuranceProductQueryHandler;
import br.com.itau.desafioseguros.application.query.handlers.QueryHandler;
import br.com.itau.desafioseguros.application.query.responses.GetInsuranceProductQueryResponse;
import br.com.itau.desafioseguros.domain.repositories.GetAllInsuranceProductRepository;
import br.com.itau.desafioseguros.domain.services.TariffedPriceCalculatorService;
import br.com.itau.desafioseguros.domain.repositories.AddInsuranceProductRepository;
import br.com.itau.desafioseguros.domain.services.strategy.tariffedpricecalculator.AutoTariffedPriceCalculatorStrategy;
import br.com.itau.desafioseguros.domain.services.strategy.tariffedpricecalculator.PatrimonialTariffedPriceCalculatorStrategy;
import br.com.itau.desafioseguros.domain.services.strategy.tariffedpricecalculator.ResidTariffedPriceCalculatorStrategy;
import br.com.itau.desafioseguros.domain.services.strategy.tariffedpricecalculator.TariffedPriceCalculatorStrategy;
import br.com.itau.desafioseguros.domain.services.strategy.tariffedpricecalculator.ViagemTariffedPriceCalculatorStrategy;
import br.com.itau.desafioseguros.domain.services.strategy.tariffedpricecalculator.VidaTariffedPriceCalculatorStrategy;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Set;

@Configuration
@EnableJpaRepositories("br.com.itau.desafioseguros.*")
@EntityScan("br.com.itau.desafioseguros.*")
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
    public TariffedPriceCalculatorService tariffedPriceCalculatorStrategyFactory(Set<TariffedPriceCalculatorStrategy> strategies) {
        return new TariffedPriceCalculatorService(strategies);
    }

    @Bean
    public CommandValidator commandValidator() {
        return new CommandValidator();
    }

    @Bean
    public CommandHandler<AddInsuranceProductCommand, AddInsuranceProductCommandResponse> addInsuranceProductCommandHandler(TariffedPriceCalculatorService strategyFactory,
                                                                                                                            CommandValidator validator,
                                                                                                                            AddInsuranceProductRepository repository,
                                                                                                                            EventPublisher eventPublisher) {
        return new AddInsuranceProductCommandHandler(strategyFactory, validator, repository, eventPublisher);
    }

    @Bean
    public QueryHandler<GetAllInsuranceProductsQuery, GetInsuranceProductQueryResponse> getAllInsuranceProductQueryHandler(GetAllInsuranceProductRepository getAllInsuranceProductRepository) {
        return new GetAllInsuranceProductQueryHandler(getAllInsuranceProductRepository);
    }

    @Bean
    public DomainEventHandler insuranceProductCreatedEventHandler() {
        return new InsuranceProductCreatedEventHandler();
    }

    @Bean
    public EventPublisher eventBus(Set<DomainEventHandler> handlers) {
        return new EventPublisher(handlers);
    }
}
