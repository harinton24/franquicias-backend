package com.accenture.franquicias.infrastructure.adapters.persistence.repositories;

import com.accenture.franquicias.domain.models.Sucursal;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface SucursalRepository extends ReactiveCrudRepository<Sucursal, UUID> {
}