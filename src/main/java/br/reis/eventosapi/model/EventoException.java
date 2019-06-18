package br.reis.eventosapi.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class EventoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EventoException(String message) {
        super(message);
    }

    public EventoException(String message, Throwable causa) {
        super(message, causa);
    }
}