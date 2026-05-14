package com.accenture.franquicias.infrastructure.repositories;

import com.accenture.franquicias.domain.models.Producto;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import java.util.UUID;

@Repository
public interface ProductoRepository extends ReactiveCrudRepository<Producto, UUID> {
    Flux<Producto> findBySucursalId(UUID sucursalId);

    // Consulta personalizada
    @Query("SELECT p.* FROM productos p " +
            "JOIN sucursales s ON p.sucursal_id = s.id " +
            "WHERE s.franquicia_id = :franquiciaId " +
            "AND p.stock = (SELECT MAX(p2.stock) FROM productos p2 WHERE p2.sucursal_id = s.id)")
    Flux<Producto> findTopStockProductsByFranquicia(UUID franquiciaId);
}