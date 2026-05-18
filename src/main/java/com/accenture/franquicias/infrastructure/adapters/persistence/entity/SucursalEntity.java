package com.accenture.franquicias.infrastructure.adapters.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("sucursales")
public class SucursalEntity implements Persistable<UUID> {

    @Id
    private UUID id;

    @Column("nombre")
    private String nombre;

    @Column("franquicia_id")
    private UUID franquiciaId;

    @Transient
    private boolean isNewEntry = false;

    public void setNew(boolean isNew) {
        this.isNewEntry = isNew;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return isNewEntry || id == null;
    }
}