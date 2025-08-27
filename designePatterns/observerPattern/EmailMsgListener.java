package designePatterns.observerPattern;

public class EmailMsgListener implements EventListener{
    private final String email;

    public EmailMsgListener(String email) {
        this.email = email;
    }

    public void update() {
        System.out.println("Send update to customer via email");
    }
}
