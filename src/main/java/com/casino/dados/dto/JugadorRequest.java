package com.casino.dados.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record JugadorRequest(
        @NotBlank(message = "El nombre es obligatorio") String nombre,
        @PositiveOrZero(message = "El saldo inicial no puede ser negativo") BigDecimal saldoInicial
) {
}
