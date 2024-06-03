package br.com.itau.desafioseguros.domain.event;

import java.time.LocalDateTime;

public abstract class DomainEvent {

    private final LocalDateTime ocurredOn;

    protected DomainEvent() {
        this.ocurredOn = LocalDateTime.now();
    }

    public LocalDateTime ocurredOn() {
        return ocurredOn;
    }

}
