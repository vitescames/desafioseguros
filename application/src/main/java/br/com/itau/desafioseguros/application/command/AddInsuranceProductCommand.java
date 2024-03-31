package br.com.itau.desafioseguros.application.command;

import br.com.itau.desafioseguros.application.command.validation.EnumValidator;
import br.com.itau.desafioseguros.domain.enums.InsuranceProductCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AddInsuranceProductCommand extends Command {

    @NotBlank(message = "Não deve estar nulo/vazio")
    private String nome;

    @NotBlank(message = "Não deve estar nulo/vazio")
    @EnumValidator(enumClass = InsuranceProductCategory.class)
    private String categoria;

    @NotNull
    private Float precoBase;

    public AddInsuranceProductCommand(String nome, String categoria, float precoBase) {
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
