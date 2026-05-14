package com.accenture.franquicias.infrastructure.adapters.web;

import com.accenture.franquicias.application.usecases.FranquiciaUseCase;
import com.accenture.franquicias.domain.models.Franquicia;
import com.accenture.franquicias.domain.models.Producto;
import com.accenture.franquicias.domain.models.Sucursal;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Franquicia> crearFranquicia(@Valid @RequestBody Franquicia franquicia) {
        return useCase.crearFranquicia(franquicia);
    }

    @PostMapping("/{franquiciaId}/sucursales")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Sucursal> agregarSucursal(@PathVariable UUID franquiciaId,@Valid @RequestBody Sucursal sucursal) {
        sucursal.setFranquiciaId(franquiciaId);
        return useCase.agregarSucursal(sucursal);
    }

    @PostMapping("/sucursales/{sucursalId}/productos")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Producto> agregarProducto(@PathVariable UUID sucursalId, @Valid @RequestBody Producto producto) {
        producto.setSucursalId(sucursalId);
        return useCase.agregarProducto(producto);
    }

    @DeleteMapping("/productos/{productoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> eliminarProducto(@PathVariable UUID productoId) {
        return useCase.eliminarProducto(productoId);
    }

    @PatchMapping("/productos/{productoId}/stock")
    public Mono<Producto> modificarStock(@PathVariable UUID productoId, @RequestBody Producto producto) {
        return useCase.modificarStock(productoId, producto.getStock());
    }

    @GetMapping("/{franquiciaId}/productos-max-stock")
    public Flux<Producto> obtenerMaxStockPorSucursal(@PathVariable UUID franquiciaId) {
        return useCase.obtenerProductosMasStock(franquiciaId);
    }

    @PatchMapping("/{id}/nombre")
    public Mono<Franquicia> actualizarNombreFranquicia(@PathVariable UUID id, @RequestBody Franquicia f) {
        return useCase.renombrarFranquicia(id, f.getNombre());
    }
}