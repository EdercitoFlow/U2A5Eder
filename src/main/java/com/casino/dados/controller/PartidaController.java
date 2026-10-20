package com.casino.dados.controller;

import com.casino.dados.dto.IniciarPartidaRequest;
import com.casino.dados.dto.PartidaResponse;
import com.casino.dados.dto.TiroResponse;
import com.casino.dados.service.PartidaService;
import com.casino.dados.service.TiroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/partidas")
@RequiredArgsConstructor
public class PartidaController {

    private final PartidaService partidaService;
    private final TiroService tiroService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PartidaResponse iniciarPartida(@Valid @RequestBody IniciarPartidaRequest request) {
        return PartidaResponse.from(partidaService.iniciarPartida(request));
    }

    @PostMapping("/{id}/tiros")
    @ResponseStatus(HttpStatus.CREATED)
    public TiroResponse realizarTiro(@PathVariable Long id) {
        return tiroService.realizarTiro(id);
    }

    @PutMapping("/{id}/finalizar")
    public PartidaResponse finalizarPartidaManual(@PathVariable Long id) {
        return PartidaResponse.from(partidaService.finalizarPartidaManual(id));
    }
}
