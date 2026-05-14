package com.accenture.franquicias.domain.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable; // 1. Importar
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("productos")
public class Producto implements Persistable<UUID> { // 2. Implementar Persistable

    @Id
    private UUID id;

    @NotBlank(message = "El nombre del producto no puede estar vacío")
    private String nombre;

    @Min(value = 0, message = "El stock no puede ser negativo")
    @NotNull(message = "El stock es obligatorio")
    private Integer stock;

    @Column("sucursal_id")
    private UUID sucursalId;

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