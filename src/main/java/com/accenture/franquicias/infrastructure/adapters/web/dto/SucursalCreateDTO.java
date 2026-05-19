package com.accenture.franquicias.infrastructure.adapters.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SucursalCreateDTO extends SucursalCreateOrUpdateBaseDTO {
    @NotNull(message = "El ID de la franquicia es obligatorio")
    private UUID franquiciaId;
}