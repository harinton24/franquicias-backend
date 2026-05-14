package com.accenture.franquicias.domain.models;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable; // <--- 1. Importar esta interfaz
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("franquicias")
public class Franquicia implements Persistable<UUID> {

    @Id
    private UUID id;

    @NotBlank(message = "El nombre del la franquicia no puede estar vacio")
    private String nombre;

    @Transient
    private List<Sucursal> sucursales;

    @Transient
    private boolean isNew = false;

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return isNew || id == null;
    }
}