package br.com.itau.desafioseguros.application.command;

import br.com.itau.desafioseguros.application.command.validation.EnumValidator;
import br.com.itau.desafioseguros.domain.enums.InsuranceProductCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AddInsuranceProductCommand implements Command {

    @NotBlank(message = "Não deve estar nulo/vazio")
    private final String name;

    @NotBlank(message = "Não deve estar nulo/vazio")
    @EnumValidator(enumClass = InsuranceProductCategory.class)
    private final String category;

    @NotNull(message = "Não deve estar nulo/vazio")
    private final Float basePrice;

    public AddInsuranceProductCommand(String name, String category, Float basePrice) {
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

    public float getBasePrice() {
        return basePrice;
    }
}
