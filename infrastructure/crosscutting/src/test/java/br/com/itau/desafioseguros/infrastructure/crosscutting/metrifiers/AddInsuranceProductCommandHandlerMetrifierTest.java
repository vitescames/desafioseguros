package br.com.itau.desafioseguros.infrastructure.crosscutting.metrifiers;

import br.com.itau.desafioseguros.domain.exceptions.InsuranceProductCategoryNullException;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class AddInsuranceProductCommandHandlerMetrifierTest {

    private AddInsuranceProductCommandHandlerMetrifier addInsuranceProductCommandHandlerMetrifier;

    private MeterRegistry meterRegistry;

    @BeforeEach
    void setUp() {
        meterRegistry = new SimpleMeterRegistry();
        Metrics.globalRegistry.add(meterRegistry);
        this.addInsuranceProductCommandHandlerMetrifier = new AddInsuranceProductCommandHandlerMetrifier(meterRegistry);
    }

    @AfterEach
    void tearDown() {
        meterRegistry.clear();
        Metrics.globalRegistry.clear();
    }

    @Test
    void afterThrowingAddInsuranceProductCommandHandler_test() {
        assertDoesNotThrow(() -> addInsuranceProductCommandHandlerMetrifier
                .afterThrowingAddInsuranceProductCommandHandler(new InsuranceProductCategoryNullException()));
    }

}