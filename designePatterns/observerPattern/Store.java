package designePatterns.observerPattern;

public class Store {
    private final NotificationService notificationService;

    public Store(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void newItemPromotion() {
        notificationService.notifyCustomers();
    }

    public NotificationService getNotificationService() {
        return notificationService;
    }
}
