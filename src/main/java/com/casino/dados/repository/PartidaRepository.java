package com.casino.dados.repository;

import com.casino.dados.model.Partida;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartidaRepository extends JpaRepository<Partida, Long> {
    List<Partida> findByJugadorIdOrderByFechaDesc(Long jugadorId);
}
