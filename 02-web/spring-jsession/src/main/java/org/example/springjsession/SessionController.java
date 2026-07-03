package org.example.springjsession;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {

    @GetMapping("/login")
    public String login(HttpSession httpSession) {
        httpSession.setAttribute("user", "testUser");
        return "Session started, JSESSIONID set";
    }

    @GetMapping("/secure-data")
    public String secureData(HttpSession httpSession) {
        Object user = httpSession.getAttribute("user");
        if(user == null) {
            throw new UnauthorizedAccessException();
        }
        return "This is secure data for user: " + user;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    static class UnauthorizedAccessException extends RuntimeException{
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        Object user = httpSession.getAttribute("user");
        httpSession.invalidate();
        return user + " logged out";
    }
}
