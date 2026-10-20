package com.casino.dados.controller;

import com.casino.dados.dto.JugadorRequest;
import com.casino.dados.dto.JugadorResponse;
import com.casino.dados.dto.PartidaResponse;
import com.casino.dados.dto.RecargaRequest;
import com.casino.dados.service.JugadorService;
import com.casino.dados.service.PartidaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jugadores")
@RequiredArgsConstructor
public class JugadorController {

    private final JugadorService jugadorService;
    private final PartidaService partidaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public JugadorResponse crear(@Valid @RequestBody JugadorRequest request) {
        return JugadorResponse.from(jugadorService.crear(request));
    }

    @GetMapping("/{id}")
    public JugadorResponse obtener(@PathVariable Long id) {
        return JugadorResponse.from(jugadorService.obtener(id));
    }

    @PatchMapping("/{id}/recarga")
    public JugadorResponse recargarSaldo(@PathVariable Long id, @Valid @RequestBody RecargaRequest request) {
        return JugadorResponse.from(jugadorService.recargarSaldo(id, request));
    }

    @GetMapping("/{id}/partidas")
    public List<PartidaResponse> historial(@PathVariable Long id) {
        return partidaService.obtenerHistorialPartida(id).stream().map(PartidaResponse::from).toList();
    }
}
