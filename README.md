# Casino Dados - Spring Boot

Requisitos: Java 17, Maven, MySQL corriendo en localhost:3306.
Ajusta usuario/password en `src/main/resources/application.properties`.
La base `casino_db` y las tablas se crean solas al arrancar.

    mvn spring-boot:run

## Endpoints
| Metodo | URL | Descripcion |
|---|---|---|
| POST | /api/jugadores | Crear jugador `{"nombre":"Ana","saldoInicial":100}` |
| GET | /api/jugadores/{id} | Ver jugador |
| PATCH | /api/jugadores/{id}/recarga | recargarSaldo `{"monto":50}` |
| GET | /api/jugadores/{id}/partidas | obtenerHistorialPartida |
| POST | /api/partidas | iniciarPartida `{"jugadorId":1,"apuesta":10}` |
| POST | /api/partidas/{id}/tiros | realizarTiro |
| PUT | /api/partidas/{id}/finalizar | finalizarPartidaManual |
