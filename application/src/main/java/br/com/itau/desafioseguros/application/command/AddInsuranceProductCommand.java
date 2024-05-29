package br.com.itau.desafioseguros.application.command;

import br.com.itau.desafioseguros.application.command.validation.EnumValidator;
import br.com.itau.desafioseguros.domain.valueobjects.InsuranceProductCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class AddInsuranceProductCommand implements Command {

    @NotBlank(message = "O nome do produto de seguro não deve estar nulo/vazio")
    private final String name;

    @NotBlank(message = "A categoria do produto de seguro não deve estar nula/vazia")
    @EnumValidator(enumClass = InsuranceProductCategory.class, message = "A categoria do produto de seguro deve ser válida")
    private final String category;

    @NotNull(message = "O preço base do produto de seguro não deve estar nulo/vazio")
    private final BigDecimal basePrice;

    public AddInsuranceProductCommand(String name, String category, BigDecimal basePrice) {
        this.name = name;
        this.category = category;
        this.basePrice = basePrice;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }
}
