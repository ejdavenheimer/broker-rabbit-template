package ar.edu.utn.frba.ddsi.receptor.messaging;

import ar.edu.utn.frba.ddsi.receptor.dto.MensajeEvento;
import ar.edu.utn.frba.ddsi.receptor.services.ProcesadorEventoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Consume los eventos de la cola y delega el procesamiento al service.
 * Esta es la "puerta de entrada" asincrónica: reemplaza a un @PostMapping REST.
 */
@Component
public class EventoListener {

  private static final Logger log = LoggerFactory.getLogger(EventoListener.class);

  private final ProcesadorEventoService procesadorEventoService;

  public EventoListener(ProcesadorEventoService procesadorEventoService) {
    this.procesadorEventoService = procesadorEventoService;
  }

  @RabbitListener(queues = "${app.events.queue}")
  public void onEvento(MensajeEvento evento) {
    log.info("Evento recibido de la cola: {}", evento.titulo());
    procesadorEventoService.procesar(evento);
  }
}
