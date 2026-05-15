package com.accenture.franquicias.application.usecases;

import com.accenture.franquicias.domain.models.Franquicia;
import com.accenture.franquicias.domain.models.Producto;
import com.accenture.franquicias.domain.models.Sucursal;
import com.accenture.franquicias.domain.ports.FranquiciaRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FranquiciaUseCaseTest {

    @Mock
    private FranquiciaRepositoryPort repositoryPort;

    @InjectMocks
    private FranquiciaUseCase useCase;

    @Test
    void crearFranquicia_DebeAsignarIdYSetearNew() {
        // Arrange
        Franquicia franquicia = new Franquicia();
        franquicia.setNombre("Franquicia Armenia");

        when(repositoryPort.saveFranquicia(any(Franquicia.class)))
                .thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));

        // Act & Assert
        StepVerifier.create(useCase.crearFranquicia(franquicia))
                .assertNext(f -> {
                    assertNotNull(f.getId());
                    assertTrue(f.isNew());
                    assertEquals("Franquicia Armenia", f.getNombre());
                })
                .verifyComplete();
    }

    @Test
    void modificarStock_DebeFallar_CuandoStockEsNegativo() {
        // Arrange
        UUID productoId = UUID.randomUUID();
        Integer stockInvalido = -10;

        // Act & Assert
        StepVerifier.create(useCase.modificarStock(productoId, stockInvalido))
                .expectErrorMatches(throwable -> throwable instanceof IllegalArgumentException &&
                        throwable.getMessage().equals("El nuevo stock no puede ser negativo"))
                .verify();
    }

    @Test
    void agregarSucursal_DebeAsignarIdYGuardar() {
        // Arrange
        Sucursal sucursal = new Sucursal();
        sucursal.setNombre("Sucursal Centro");

        when(repositoryPort.saveSucursal(any(Sucursal.class)))
                .thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));

        // Act & Assert
        StepVerifier.create(useCase.agregarSucursal(sucursal))
                .assertNext(s -> {
                    assertNotNull(s.getId());
                    assertTrue(s.isNew());
                    assertEquals("Sucursal Centro", s.getNombre());
                })
                .verifyComplete();
    }


    @Test
    void obtenerProductosMasStock_DebeRetornarFlujoDePuerto() {
        // Arrange
        UUID franquiciaId = UUID.randomUUID();
        Producto p1 = new Producto();
        p1.setNombre("Producto Top 1");
        p1.setStock(500);

        when(repositoryPort.findTopStockProductsByFranquicia(franquiciaId))
                .thenReturn(Flux.just(p1));

        // Act & Assert
        StepVerifier.create(useCase.obtenerProductosMasStock(franquiciaId))
                .expectNext(p1)
                .verifyComplete();
    }

    @Test
    void agregarProducto_DebeAsignarIdYGuardarCorrectamente() {
        // Arrange
        Producto producto = new Producto();
        producto.setNombre("Producto Test");
        producto.setStock(10);

        when(repositoryPort.saveProducto(any(Producto.class)))
                .thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));

        // Act & Assert
        StepVerifier.create(useCase.agregarProducto(producto))
                .assertNext(p -> {
                    assertNotNull(p.getId());
                    assertTrue(p.isNew());
                    assertEquals("Producto Test", p.getNombre());
                })
                .verifyComplete();
    }

    @Test
    void eliminarProducto_DebeLlamarAlPuerto() {
        // Arrange
        UUID productoId = UUID.randomUUID();
        when(repositoryPort.deleteProducto(productoId)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(useCase.eliminarProducto(productoId))
                .verifyComplete();
    }

    @Test
    void renombrarFranquicia_DebeRetornarFranquiciaActualizada() {
        // Arrange
        UUID id = UUID.randomUUID();
        String nuevoNombre = "Nuevo Nombre";
        Franquicia franquiciaActualizada = new Franquicia();
        franquiciaActualizada.setId(id);
        franquiciaActualizada.setNombre(nuevoNombre);

        when(repositoryPort.updateNombreFranquicia(id, nuevoNombre))
                .thenReturn(Mono.just(franquiciaActualizada));

        // Act & Assert
        StepVerifier.create(useCase.renombrarFranquicia(id, nuevoNombre))
                .expectNextMatches(f -> f.getNombre().equals(nuevoNombre))
                .verifyComplete();
    }
}