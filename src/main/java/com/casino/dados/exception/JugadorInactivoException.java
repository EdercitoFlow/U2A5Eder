package com.casino.dados.exception;

public class JugadorInactivoException extends ReglaNegocioException {
    public JugadorInactivoException(String mensaje) {
        super(mensaje);
    }
}
