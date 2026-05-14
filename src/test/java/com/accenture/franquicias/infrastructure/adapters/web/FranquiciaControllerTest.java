package com.accenture.franquicias.infrastructure.adapters.web;

import com.accenture.franquicias.application.usecases.FranquiciaUseCase;
import com.accenture.franquicias.domain.models.Franquicia;
import com.accenture.franquicias.domain.models.Producto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest(controllers = FranquiciaController.class)
class FranquiciaControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private FranquiciaUseCase useCase; // Mockeamos el caso de uso para no tocar la DB

    // 1. TEST: VALIDACIÓN DE DATOS (BAD REQUEST)
    @Test
    void crearFranquicia_CuandoNombreEsVacio_DebeRetornar400() {
        // Arrange
        Franquicia franquiciaInvalida = new Franquicia();
        franquiciaInvalida.setNombre("");

        // Act & Assert
        webTestClient.post()
                .uri("/api/franquicias")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(franquiciaInvalida)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void crearFranquicia_CuandoTodoEsCorrecto_DebeRetornar201() {
        // Arrange
        Franquicia franquicia = new Franquicia();
        franquicia.setNombre("Accenture Store");

        when(useCase.crearFranquicia(any(Franquicia.class)))
                .thenReturn(Mono.just(franquicia));

        // Act & Assert
        webTestClient.post()
                .uri("/api/franquicias")
                .bodyValue(franquicia)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.nombre").isEqualTo("Accenture Store");
    }

    @Test
    void obtenerProductosMasStock_DebeRetornarListaDeProductos() {
        // Arrange
        UUID franquiciaId = UUID.randomUUID();
        Producto p = new Producto();
        p.setNombre("Producto Top");
        p.setStock(100);

        when(useCase.obtenerProductosMasStock(franquiciaId))
                .thenReturn(Flux.just(p));

        // ACT & ASSERT
        webTestClient.get()
                .uri("/api/franquicias/{id}/productos-max-stock", franquiciaId)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(Producto.class)
                .hasSize(1)
                .contains(p);
    }
}