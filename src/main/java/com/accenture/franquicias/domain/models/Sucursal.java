package com.accenture.franquicias.domain.models;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable; // 1. Importar
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;

import java.util.UUID;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("sucursales")
public class Sucursal implements Persistable<UUID> {

    @Id
    private UUID id;

    @NotBlank(message = "El nombre del la scoursal no puede estar vacio")
    private String nombre;

    @Column("franquicia_id")
    private UUID franquiciaId;

    @Transient
    private List<Producto> productos;

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