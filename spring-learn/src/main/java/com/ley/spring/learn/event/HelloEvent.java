package com.ley.spring.learn.event;

import org.springframework.context.ApplicationEvent;

public class HelloEvent extends ApplicationEvent {

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public HelloEvent(Object source) {
        super(source);
    }
}