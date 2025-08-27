package designePatterns.observerPattern;

public class ObserverMain {
    public static void main(String[] args) {
        Store store = new Store(new NotificationService());
        NotificationService notificationService = store.getNotificationService();

        notificationService.subscribe(
                new EmailMsgListener("Mark@yahoo.com")
        );
        notificationService.subscribe(
                new EmailMsgListener("clara@google.com")
        );
        notificationService.subscribe(
                new MobileAppListener("Geeky")
        );

        store.newItemPromotion();
    }
}
