package br.com.itau.desafioseguros.infrastructure.crosscutting.metrifiers;

import br.com.itau.desafioseguros.domain.exceptions.BaseException;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AddInsuranceProductCommandHandlerMetrifier {

    private final MeterRegistry meterRegistry;

    public AddInsuranceProductCommandHandlerMetrifier(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @AfterThrowing(value = "execution(* br.com.itau.desafioseguros.application.command.handlers.AddInsuranceProductCommandHandler.*(..))", throwing = "ex")
    public void afterThrowingAddInsuranceProductCommandHandler(BaseException ex) {
        Counter.builder("add.insurance.product.error")
                .tags("erro", ex.getClass().getSimpleName())
                .register(meterRegistry)
                .increment();
    }

}
