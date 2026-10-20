package com.casino.dados.dto;

import com.casino.dados.model.Jugador;

import java.math.BigDecimal;

public record JugadorResponse(Long id, String nombre, BigDecimal saldo, boolean activo) {
    public static JugadorResponse from(Jugador j) {
        return new JugadorResponse(j.getId(), j.getNombre(), j.getSaldo(), j.isActivo());
    }
}
