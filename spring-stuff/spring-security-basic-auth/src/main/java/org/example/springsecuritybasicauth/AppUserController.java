package org.example.springsecuritybasicauth;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody RegisterRequest registerRequest, HttpServletRequest httpRequest) {
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(registerRequest.username(), registerRequest.password())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            httpRequest.getSession(true);
            return new ResponseEntity<>("User logged in successfully", HttpStatus.OK);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
        }
    }


    @GetMapping("/me")
    public ResponseEntity<String> me(@AuthenticationPrincipal UserDetails userDetails) {
        return new ResponseEntity<>("Logged in as: " + userDetails.getUsername(), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@AuthenticationPrincipal UserDetails userDetails, HttpServletRequest httpRequest) {
        String username = userDetails.getUsername();
        appUserRepository.deleteByUsername(username);
        httpRequest.getSession().invalidate();
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }
}
