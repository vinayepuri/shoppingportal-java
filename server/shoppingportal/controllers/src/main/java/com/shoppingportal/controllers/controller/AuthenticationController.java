package com.ecommerce.controllers.controller;

import com.ecommerce.controllers.handler.AuthenticationHandler;
import com.ecommerce.domain.model.auth.AuthRequest;
import com.ecommerce.domain.model.auth.AuthResponse;
import com.ecommerce.domain.model.auth.SignupRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationHandler authenticationDriverPort;

    public AuthenticationController(AuthenticationHandler authenticationDriverPort) {
        this.authenticationDriverPort = authenticationDriverPort;
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> authenticateUser(@Valid @RequestBody AuthRequest authRequest) {
            return ResponseEntity.ok(authenticationDriverPort.signIn(authRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signupUser(@Valid @RequestBody SignupRequest signupRequest) {
        authenticationDriverPort.signUp(signupRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
