package com.accenture.franquicias.infrastructure.adapters.persistence.repositories;

import com.accenture.franquicias.domain.models.Franquicia;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface FranquiciaRepository extends ReactiveCrudRepository<Franquicia, UUID> {
}