package designePatterns.behavioralPatterns.observerPattern;

import java.util.ArrayList;

public class NotificationService {
    private final ArrayList<EventListener> customers;

    public NotificationService() {
        this.customers = new ArrayList<>();
    }

    public void subscribe(EventListener customer) {
        customers.add(customer);
    }

    public void unsubscribe(EventListener customer) {
        customers.remove(customer);
    }

    public void notifyCustomers() {
        customers.forEach(EventListener::update);
    }
}
