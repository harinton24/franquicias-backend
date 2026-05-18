package com.accenture.franquicias.infrastructure.adapters.persistence.adapters;

import com.accenture.franquicias.domain.models.Franquicia;
import com.accenture.franquicias.domain.models.Producto;
import com.accenture.franquicias.domain.models.Sucursal;
import com.accenture.franquicias.domain.ports.FranquiciaRepositoryPort;
import com.accenture.franquicias.infrastructure.adapters.persistence.entity.FranquiciaEntity;
import com.accenture.franquicias.infrastructure.adapters.persistence.entity.ProductoEntity;
import com.accenture.franquicias.infrastructure.adapters.persistence.entity.SucursalEntity;
import com.accenture.franquicias.infrastructure.adapters.persistence.mappers.FranquiciaEntityMapper;
import com.accenture.franquicias.infrastructure.adapters.persistence.mappers.ProductoEntityMapper;
import com.accenture.franquicias.infrastructure.adapters.persistence.mappers.SucursalEntityMapper;
import com.accenture.franquicias.infrastructure.adapters.persistence.repositories.FranquiciaRepository;
import com.accenture.franquicias.infrastructure.adapters.persistence.repositories.ProductoRepository;
import com.accenture.franquicias.infrastructure.adapters.persistence.repositories.SucursalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FranquiciaPersistenceAdapter implements FranquiciaRepositoryPort {

    private final FranquiciaRepository franquiciaRepository;
    private final SucursalRepository sucursalRepository;
    private final ProductoRepository productoRepository;

    private final FranquiciaEntityMapper franquiciaMapper;
    private final SucursalEntityMapper sucursalMapper;
    private final ProductoEntityMapper productoMapper;

    @Override
    public Mono<Franquicia> saveFranquicia(Franquicia franquicia) {
        FranquiciaEntity entity = franquiciaMapper.toEntity(franquicia);
        entity.setNew(true);
        return franquiciaRepository.save(entity)
                .map(franquiciaMapper::toDomain);
    }

    @Override
    public Mono<Franquicia> updateNombreFranquicia(UUID id, String nuevoNombre) {
        return franquiciaRepository.findById(id)
                .flatMap(f -> {
                    f.setNombre(nuevoNombre);
                    return franquiciaRepository.save(f);
                })
                .map(franquiciaMapper::toDomain);
    }

    @Override
    public Mono<Sucursal> saveSucursal(Sucursal sucursal) {
        SucursalEntity entity = sucursalMapper.toEntity(sucursal);
        entity.setNew(true);
        return sucursalRepository.save(entity)
                .map(sucursalMapper::toDomain);
    }

    @Override
    public Mono<Sucursal> updateNombreSucursal(UUID id, String nuevoNombre) {
        return sucursalRepository.findById(id)
                .flatMap(s -> {
                    s.setNombre(nuevoNombre);
                    return sucursalRepository.save(s);
                })
                .map(sucursalMapper::toDomain);
    }

    @Override
    public Mono<Producto> saveProducto(Producto producto) {
        ProductoEntity entity = productoMapper.toEntity(producto);
        entity.setNew(true);
        return productoRepository.save(entity)
                .map(productoMapper::toDomain);
    }

    @Override
    public Mono<Void> deleteProducto(UUID id) {
        return productoRepository.deleteById(id);
    }

    @Override
    public Mono<Producto> updateStock(UUID productoId, Integer nuevoStock) {
        return productoRepository.findById(productoId)
                .flatMap(p -> {
                    p.setStock(nuevoStock);
                    return productoRepository.save(p);
                })
                .map(productoMapper::toDomain);
    }

    @Override
    public Mono<Producto> updateNombreProducto(UUID productoId, String nuevoNombre) {
        return productoRepository.findById(productoId)
                .flatMap(p -> {
                    p.setNombre(nuevoNombre);
                    return productoRepository.save(p);
                })
                .map(productoMapper::toDomain);
    }

    @Override
    public Flux<Producto> findTopStockProductsByFranquicia(UUID franquiciaId) {
        return productoRepository.findTopStockProductsByFranquicia(franquiciaId)
                .map(productoMapper::toDomain);
    }
}