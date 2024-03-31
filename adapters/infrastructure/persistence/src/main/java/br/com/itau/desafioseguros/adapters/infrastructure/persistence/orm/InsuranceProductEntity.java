package br.com.itau.desafioseguros.adapters.infrastructure.persistence.orm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "insurance_product")
public class InsuranceProductEntity {

    @Id
    private UUID id;

    private String name;

    private String category;

    @Column(name = "base_price")
    private Float basePrice;

    @Column(name = "tariffed_price")
    private Float tariffedPrice;

    public InsuranceProductEntity() {
    }

    public InsuranceProductEntity(UUID id, String name, String category, Float basePrice, Float tariffedPrice) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.basePrice = basePrice;
        this.tariffedPrice = tariffedPrice;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Float getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Float basePrice) {
        this.basePrice = basePrice;
    }

    public Float getTariffedPrice() {
        return tariffedPrice;
    }

    public void setTariffedPrice(Float tariffedPrice) {
        this.tariffedPrice = tariffedPrice;
    }
}
