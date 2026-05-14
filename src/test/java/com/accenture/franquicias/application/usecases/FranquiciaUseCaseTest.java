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
}