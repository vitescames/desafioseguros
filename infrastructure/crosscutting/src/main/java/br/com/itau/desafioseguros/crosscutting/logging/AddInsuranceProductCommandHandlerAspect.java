package br.com.itau.desafioseguros.crosscutting.logging;

import br.com.itau.desafioseguros.domain.exceptions.BaseException;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AddInsuranceProductCommandHandlerAspect {

    @Autowired
    MeterRegistry meterRegistry;

    @AfterThrowing(value = "execution(* br.com.itau.desafioseguros.application.command.handler.AddInsuranceProductCommandHandler.*(..))", throwing = "ex")
    public void afterThrowing(BaseException ex) {
        Counter.builder("add.insurance.product.error")
                .tags("erro", ex.getClass().getSimpleName())
                .register(meterRegistry)
                .increment();
    }

}
