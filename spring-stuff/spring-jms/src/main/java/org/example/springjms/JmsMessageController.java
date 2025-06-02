package org.example.springjms;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JmsMessageController {

    JmsSenderService jmsSenderService;

    public JmsMessageController(JmsSenderService jmsSenderService) {
        this.jmsSenderService = jmsSenderService;
    }

    @PostMapping("/message")
    public ResponseEntity<Void> sendMessage(@RequestBody JmsMessage jmsMessage) {
        if(jmsMessage.getMessage() == null) {
            throw new IllegalArgumentException("Message must not be null");
        }

        jmsSenderService.send(jmsMessage.getMessage());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
