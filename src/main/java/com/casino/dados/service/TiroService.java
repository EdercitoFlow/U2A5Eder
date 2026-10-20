package com.casino.dados.service;

import com.casino.dados.dto.TiroResponse;
import com.casino.dados.exception.PartidaEstadoInvalidaException;
import com.casino.dados.model.EstadoPartida;
import com.casino.dados.model.Jugador;
import com.casino.dados.model.Partida;
import com.casino.dados.model.Tiro;
import com.casino.dados.repository.JugadorRepository;
import com.casino.dados.repository.PartidaRepository;
import com.casino.dados.repository.TiroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class TiroService {

    private final TiroRepository tiroRepository;
    private final PartidaRepository partidaRepository;
    private final JugadorRepository jugadorRepository;
    private final PartidaService partidaService;

    private final Random random = new Random();


    @Transactional
    public TiroResponse realizarTiro(Long partidaId) {
        Partida partida = partidaService.obtener(partidaId); // valida que exista

        if (partida.getEstado() != EstadoPartida.EN_JUEGO) {
            throw new PartidaEstadoInvalidaException(
                    "La partida " + partidaId + " no esta EN_JUEGO (estado: " + partida.getEstado() + ")");
        }

        int dado1 = random.nextInt(6) + 1;
        int dado2 = random.nextInt(6) + 1;
        int suma = dado1 + dado2;

        boolean gana = suma == 7 || suma == 11;
        boolean pierde = suma == 2 || suma == 3 || suma == 12;
        String resultado = "CONTINUA";

        Jugador jugador = partida.getJugador();

        if (gana) {
            partida.setEstado(EstadoPartida.FINALIZADA);
            jugador.setSaldo(jugador.getSaldo().add(partida.getApuesta().multiply(BigDecimal.valueOf(2))));
            jugadorRepository.save(jugador);
            partidaRepository.save(partida);
            resultado = "GANASTE";
        } else if (pierde) {
            partida.setEstado(EstadoPartida.FINALIZADA);
            partidaRepository.save(partida);
            resultado = "PERDISTE";
        }

        // Ultimo paso: guardar el tiro
        Tiro tiro = new Tiro();
        tiro.setPartida(partida);
        tiro.setValorDado1(dado1);
        tiro.setValorDado2(dado2);
        tiro.setSuma(suma);
        tiro.setEsGanador(gana);
        Tiro guardado = tiroRepository.save(tiro);

        return TiroResponse.from(guardado, resultado, jugador.getSaldo());
    }
}
