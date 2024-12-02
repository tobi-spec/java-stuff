package designePatterns.behavioralPatterns.observerPattern;

import java.util.ArrayList;

public class NotificationService {
    private final ArrayList<EmailMsgListener> customers;

    public NotificationService() {
        this.customers = new ArrayList<>();
    }

    public void subscribe(EmailMsgListener customer) {
        customers.add(customer);
    }

    public void unsubscribe(EmailMsgListener customer) {
        customers.remove(customer);
    }

    public void notifyCustomers() {
        customers.forEach(EmailMsgListener::update);
    }
}
