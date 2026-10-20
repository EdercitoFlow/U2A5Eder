package com.casino.dados.dto;

import com.casino.dados.model.EstadoPartida;
import com.casino.dados.model.Tiro;

import java.math.BigDecimal;

public record TiroResponse(Long id, Long partidaId, int valorDado1, int valorDado2, int suma,
                           boolean esGanador, String resultado,
                           EstadoPartida estadoPartida, BigDecimal saldoJugador) {
    public static TiroResponse from(Tiro t, String resultado, BigDecimal saldoJugador) {
        return new TiroResponse(t.getId(), t.getPartida().getId(), t.getValorDado1(),
                t.getValorDado2(), t.getSuma(), t.isEsGanador(), resultado,
                t.getPartida().getEstado(), saldoJugador);
    }
}
