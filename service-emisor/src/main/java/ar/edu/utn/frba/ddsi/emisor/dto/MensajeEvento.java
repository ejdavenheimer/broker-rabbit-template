package ar.edu.utn.frba.ddsi.emisor.dto;

import java.time.LocalDateTime;

/**
 * Ejemplo de evento que se publica en el broker.
 * Reemplazá los campos por los de tu dominio (lo importante es que el receptor
 * tenga un record/clase con los MISMOS nombres de campo para deserializarlo).
 */
public record MensajeEvento(
    String titulo,
    String contenido,
    LocalDateTime fechaHora
) {}
