package org.example.springevents;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final ApplicationEventPublisher publisher;

    public OrderService(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    public void createOrder(Order order) {
        System.out.println("Create Order");
        publisher.publishEvent(new OrderCreatedEvent(this, order.getId()));
    }
}
