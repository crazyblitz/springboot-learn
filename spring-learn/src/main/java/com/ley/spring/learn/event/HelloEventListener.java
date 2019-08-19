package com.ley.spring.learn.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author Aliue
 */
@Component
public class HelloEventListener implements ApplicationListener<HelloEvent> {

    @Override
    public void onApplicationEvent(HelloEvent event) {
        System.out.println(event.getSource());
    }
}
