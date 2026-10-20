package com.casino.dados.service;

import com.casino.dados.dto.JugadorRequest;
import com.casino.dados.dto.RecargaRequest;
import com.casino.dados.exception.RecursoNoEncontradoException;
import com.casino.dados.model.Jugador;
import com.casino.dados.repository.JugadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class JugadorService {

    private final JugadorRepository jugadorRepository;

    @Transactional
    public Jugador crear(JugadorRequest request) {
        Jugador j = new Jugador();
        j.setNombre(request.nombre());
        j.setSaldo(request.saldoInicial() != null ? request.saldoInicial() : BigDecimal.ZERO);
        j.setActivo(true);
        return jugadorRepository.save(j);
    }

    @Transactional(readOnly = true)
    public Jugador obtener(Long id) {
        return jugadorRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("El jugador con id " + id + " no existe"));
    }

    /** recargarSaldo: suma saldo al jugador. */
    @Transactional
    public Jugador recargarSaldo(Long id, RecargaRequest request) {
        Jugador jugador = obtener(id);
        jugador.setSaldo(jugador.getSaldo().add(request.monto()));
        return jugadorRepository.save(jugador);
    }
}
