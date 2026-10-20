package com.casino.dados.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tiros")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Tiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "partida_id")
    private Partida partida;

    private int valorDado1;
    private int valorDado2;
    private int suma;
    private boolean esGanador;
}
