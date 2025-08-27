package designePatterns.observerPattern;

public class MobileAppListener implements EventListener{
    private final String mobileApp;

    public MobileAppListener(String mobileApp) {
        this.mobileApp = mobileApp;
    }

    public void update() {
        System.out.println("Send update to customer via mobile app");
    }
}
