package br.com.itau.desafioseguros.application.command;

import br.com.itau.desafioseguros.application.command.validation.EnumValidator;
import br.com.itau.desafioseguros.domain.enums.InsuranceProductCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AddInsuranceProductCommand implements Command {

    @NotBlank(message = "Não deve estar nulo/vazio")
    private final String nome;

    @NotBlank(message = "Não deve estar nulo/vazio")
    @EnumValidator(enumClass = InsuranceProductCategory.class)
    private final String categoria;

    @NotNull(message = "Não deve estar nulo/vazio")
    private final Float precoBase;

    public AddInsuranceProductCommand(String nome, String categoria, Float precoBase) {
        this.nome = nome;
        this.categoria = categoria;
        this.precoBase = precoBase;
    }

    public String getNome() {
        return nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public float getPrecoBase() {
        return precoBase;
    }
}
