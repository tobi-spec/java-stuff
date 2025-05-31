package org.example.springsecurityformloginauth;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class AppUserController {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AppUserController(AppUserRepository appUserRepository,
                             PasswordEncoder passwordEncoder,
                             AuthenticationManager authenticationManager) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/live")
    public String live() {
        return "Server is live";
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        if(appUserRepository.findByUsername(registerRequest.username()).isPresent()) {
            return new ResponseEntity<>("User already exists", HttpStatus.CONFLICT);
        }

        AppUser appUser = new AppUser(registerRequest.username(), passwordEncoder.encode(registerRequest.password()));
        appUserRepository.save(appUser);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }


    @GetMapping("/me")
    public ResponseEntity<String> me() {
        return new ResponseEntity<>("Logged in as", HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@AuthenticationPrincipal UserDetails userDetails, HttpServletRequest httpRequest) {
        String username = userDetails.getUsername();
        appUserRepository.deleteByUsername(username);
        httpRequest.getSession().invalidate();
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }
}
