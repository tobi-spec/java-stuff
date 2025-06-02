package org.example.springjms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JmsReceiver {

    @JmsListener(destination = "myQueue")
    public void receiveMessage(String message) {
        System.out.println("Received message: " + message + "!!!!!!!!!");
    }
}
