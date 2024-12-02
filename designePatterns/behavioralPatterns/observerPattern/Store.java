package designePatterns.behavioralPatterns.observerPattern;

public class Store {
    private final NotificationService notificationService;

    public Store() {
        this.notificationService = new NotificationService();
    }

    public void newItemPromotion() {
        notificationService.notify();
    }

    public NotificationService getNotificationService() {
        return notificationService;
    }
}
