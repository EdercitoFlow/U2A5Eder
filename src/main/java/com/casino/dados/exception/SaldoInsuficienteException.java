package com.casino.dados.exception;

public class SaldoInsuficienteException extends ReglaNegocioException {
    public SaldoInsuficienteException(String mensaje) {
        super(mensaje);
    }
}
