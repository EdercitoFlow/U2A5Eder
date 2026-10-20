package com.casino.dados.dto;

import com.casino.dados.model.EstadoPartida;
import com.casino.dados.model.Partida;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PartidaResponse(Long id, LocalDateTime fecha, EstadoPartida estado,
                              Long jugadorId, BigDecimal apuesta) {
    public static PartidaResponse from(Partida p) {
        return new PartidaResponse(p.getId(), p.getFecha(), p.getEstado(),
                p.getJugador().getId(), p.getApuesta());
    }
}
