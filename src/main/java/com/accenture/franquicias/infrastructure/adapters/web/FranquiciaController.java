package com.accenture.franquicias.infrastructure.adapters.web;

import com.accenture.franquicias.application.mappers.FranquiciaMapper;
import com.accenture.franquicias.application.mappers.ProductoMapper;
import com.accenture.franquicias.application.mappers.SucursalMapper;
import com.accenture.franquicias.application.usecases.FranquiciaUseCase;
import com.accenture.franquicias.infrastructure.dto.FranquiciaDTO;
import com.accenture.franquicias.infrastructure.dto.ProductoDTO;
import com.accenture.franquicias.infrastructure.dto.ProductoMasStockDTO;
import com.accenture.franquicias.infrastructure.dto.SucursalDTO;
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
    public Mono<FranquiciaDTO> crearFranquicia(@Valid @RequestBody FranquiciaDTO dto) {
        return useCase.crearFranquicia(franquiciaMapper.toEntity(dto))
                .map(franquiciaMapper::toDTO);
    }

    @PostMapping("/{franquiciaId}/sucursales")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<SucursalDTO> agregarSucursal(
            @PathVariable UUID franquiciaId,
            @Valid @RequestBody SucursalDTO dto) {
        dto.setFranquiciaId(franquiciaId);
        return useCase.agregarSucursal(sucursalMapper.toEntity(dto))
                .map(sucursalMapper::toDTO);
    }

    @PostMapping("/sucursales/{sucursalId}/productos")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ProductoDTO> agregarProducto(
            @PathVariable UUID sucursalId,
            @Valid @RequestBody ProductoDTO dto) {
        dto.setSucursalId(sucursalId);
        return useCase.agregarProducto(productoMapper.toEntity(dto))
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
            @Valid @RequestBody FranquiciaDTO dto) {
        return useCase.renombrarFranquicia(id, dto.getNombre())
                .map(franquiciaMapper::toDTO);
    }
}