package br.com.itau.desafioseguros.infrastructure.persistence.repositories;

import br.com.itau.desafioseguros.infrastructure.persistence.orm.InsuranceProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InsuranceProductRepository extends JpaRepository<InsuranceProductEntity, UUID> {
}
