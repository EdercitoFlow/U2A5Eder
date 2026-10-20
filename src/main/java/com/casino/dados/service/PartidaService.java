package com.casino.dados.service;

import com.casino.dados.dto.IniciarPartidaRequest;
import com.casino.dados.exception.JugadorInactivoException;
import com.casino.dados.exception.PartidaEstadoInvalidaException;
import com.casino.dados.exception.RecursoNoEncontradoException;
import com.casino.dados.exception.SaldoInsuficienteException;
import com.casino.dados.model.EstadoPartida;
import com.casino.dados.model.Jugador;
import com.casino.dados.model.Partida;
import com.casino.dados.repository.JugadorRepository;
import com.casino.dados.repository.PartidaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PartidaService {

    private final PartidaRepository partidaRepository;
    private final JugadorRepository jugadorRepository;
    private final JugadorService jugadorService;

    /** iniciarPartida: valida jugador activo y saldo, descuenta apuesta, crea partida EN_JUEGO. */
    @Transactional
    public Partida iniciarPartida(IniciarPartidaRequest request) {
        Jugador jugador = jugadorService.obtener(request.jugadorId());

        if (!jugador.isActivo()) {
            throw new JugadorInactivoException("El jugador " + jugador.getNombre() + " no esta activo");
        }
        if (jugador.getSaldo().compareTo(request.apuesta()) < 0) {
            throw new SaldoInsuficienteException("Saldo insuficiente. Saldo actual: "
                    + jugador.getSaldo() + ", apuesta: " + request.apuesta());
        }

        jugador.setSaldo(jugador.getSaldo().subtract(request.apuesta()));
        jugadorRepository.save(jugador);

        Partida partida = new Partida();
        partida.setFecha(LocalDateTime.now());
        partida.setEstado(EstadoPartida.EN_JUEGO);
        partida.setJugador(jugador);
        partida.setApuesta(request.apuesta());
        return partidaRepository.save(partida);
    }

    @Transactional(readOnly = true)
    public Partida obtener(Long id) {
        return partidaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("La partida con id " + id + " no existe"));
    }

    /** finalizarPartidaManual: cierra una partida que quedo en curso. */
    @Transactional
    public Partida finalizarPartidaManual(Long id) {
        Partida partida = obtener(id);
        if (partida.getEstado() == EstadoPartida.FINALIZADA) {
            throw new PartidaEstadoInvalidaException("La partida " + id + " ya esta finalizada");
        }
        partida.setEstado(EstadoPartida.FINALIZADA);
        return partidaRepository.save(partida);
    }

    /** obtenerHistorialPartida: lista las partidas del jugador. */
    @Transactional(readOnly = true)
    public List<Partida> obtenerHistorialPartida(Long jugadorId) {
        jugadorService.obtener(jugadorId); // valida que el jugador exista
        return partidaRepository.findByJugadorIdOrderByFechaDesc(jugadorId);
    }
}
