package com.mylibrary.infrastructure.configuration.event;

import com.mylibrary.domain.EventPublisher;
import com.mylibrary.infrastructure.events.SpringEventPublisher;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventPublisherConfig {
    ApplicationEventPublisher applicationEventPublisher;

    public EventPublisherConfig(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Bean
    public EventPublisher eventPublisher() {
        return new SpringEventPublisher(applicationEventPublisher);
    }
}
