package com.mylibrary.infrastructure.events;

import com.mylibrary.domain.EventPublisher;
import org.springframework.context.ApplicationEventPublisher;

public class SpringEventPublisher implements EventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public SpringEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void send(Object event) {
        applicationEventPublisher.publishEvent(event);
    }
}
