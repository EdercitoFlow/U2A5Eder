package com.casino.dados.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record IniciarPartidaRequest(
        @NotNull(message = "El jugadorId es obligatorio") Long jugadorId,
        @NotNull(message = "La apuesta es obligatoria")
        @Positive(message = "La apuesta debe ser mayor a 0") BigDecimal apuesta
) {
}
