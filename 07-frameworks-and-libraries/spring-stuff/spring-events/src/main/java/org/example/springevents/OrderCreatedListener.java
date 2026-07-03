package org.example.springevents;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class OrderCreatedListener {

    @EventListener
    public void onOrderCreated(OrderCreatedEvent event) {
        System.out.println("Event Listener - Received Order: " + event.getOrderId());
    }
}
