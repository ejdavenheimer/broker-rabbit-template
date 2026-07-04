package ar.edu.utn.frba.ddsi.emisor.controllers;

import ar.edu.utn.frba.ddsi.emisor.dto.MensajeEvento;
import ar.edu.utn.frba.ddsi.emisor.messaging.EventoPublisher;
import java.time.LocalDateTime;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Endpoint de ejemplo para disparar la publicación de un evento a mano.
 * Sirve para probar el broker sin esperar ningún proceso. Borralo o reemplazalo por tu lógica real.
 */
@RestController
@RequestMapping("/emisor/eventos")
public class EventoController {

  private final EventoPublisher eventoPublisher;

  public EventoController(EventoPublisher eventoPublisher) {
    this.eventoPublisher = eventoPublisher;
  }

  @PostMapping
  public ResponseEntity<String> publicar(@RequestBody MensajeEvento request) {
    var evento = new MensajeEvento(
        request.titulo(),
        request.contenido(),
        request.fechaHora() != null ? request.fechaHora() : LocalDateTime.now());
    eventoPublisher.publicar(evento);
    return ResponseEntity.accepted().body("Evento publicado en el broker.");
  }
}
