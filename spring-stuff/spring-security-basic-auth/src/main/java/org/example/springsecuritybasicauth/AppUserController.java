package org.example.springsecuritybasicauth;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class AppUserController {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AppUserController(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/live")
    public String live() {
        return "Server is live";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password) {
        if(appUserRepository.findByUsername(username).isPresent()) {
            return "User already exists";
        }

        AppUser appUser = new AppUser(username, passwordEncoder.encode(password));
        appUserRepository.save(appUser);

        return "User registered successfully";
    }

    @GetMapping("/me")
    public String me(@AuthenticationPrincipal UserDetails userDetails) {
        return "Logged in as: " + userDetails.getUsername();
    }

    @DeleteMapping("/delete")
    public String delete(@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        appUserRepository.deleteByUsername(username);

        return "User deleted successfully";
    }
}
