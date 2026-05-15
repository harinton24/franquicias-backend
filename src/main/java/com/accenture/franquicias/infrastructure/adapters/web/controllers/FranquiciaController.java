package com.accenture.franquicias.infrastructure.adapters.web.controllers;

import com.accenture.franquicias.application.mappers.FranquiciaMapper;
import com.accenture.franquicias.application.mappers.ProductoMapper;
import com.accenture.franquicias.application.mappers.SucursalMapper;
import com.accenture.franquicias.application.usecases.FranquiciaUseCase;
import com.accenture.franquicias.infrastructure.adapters.web.dto.*; // Importamos todos los DTOs
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/franquicias")
@RequiredArgsConstructor
public class FranquiciaController {

    private final FranquiciaUseCase useCase;
    private final FranquiciaMapper franquiciaMapper;
    private final SucursalMapper sucursalMapper;
    private final ProductoMapper productoMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<FranquiciaDTO> crearFranquicia(@Valid @RequestBody FranquiciaCreateDTO dto) {
        return useCase.crearFranquicia(franquiciaMapper.toEntity(dto))
                .map(franquiciaMapper::toDTO);
    }

    @PostMapping("/sucursales")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<SucursalDTO> agregarSucursal(
            @Valid @RequestBody SucursalCreateDTO dto) {
        return useCase.agregarSucursal(sucursalMapper.toEntity(dto))
                .map(sucursalMapper::toDTO);
    }

    @PostMapping("/sucursales/{sucursalId}/productos")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ProductoDTO> agregarProducto(
            @PathVariable UUID sucursalId,
            @Valid @RequestBody ProductoCreateDTO dto) {
        var producto = productoMapper.toEntity(dto);
        producto.setSucursalId(sucursalId);

        return useCase.agregarProducto(producto)
                .map(productoMapper::toDTO);
    }

    @DeleteMapping("/productos/{productoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> eliminarProducto(@PathVariable UUID productoId) {
        return useCase.eliminarProducto(productoId);
    }

    @PatchMapping("/productos/{productoId}/stock")
    public Mono<ProductoDTO> modificarStock(
            @PathVariable UUID productoId,
            @Valid @RequestBody ProductoDTO dto) {
        return useCase.modificarStock(productoId, dto.getStock())
                .map(productoMapper::toDTO);
    }

    @GetMapping("/{franquiciaId}/productos-max-stock")
    public Flux<ProductoMasStockDTO> obtenerMaxStockPorSucursal(@PathVariable UUID franquiciaId) {
        return useCase.obtenerProductosMasStock(franquiciaId)
                .map(productoMapper::toProductoMasStockDTO);
    }

    @PatchMapping("/{id}/nombre")
    public Mono<FranquiciaDTO> actualizarNombreFranquicia(
            @PathVariable UUID id,
            @Valid @RequestBody FranquiciaCreateDTO dto) {
        return useCase.renombrarFranquicia(id, dto.getNombre())
                .map(franquiciaMapper::toDTO);
    }
}