package com.backend.organisation.controller;

import com.backend.organisation.config.securityService.AuthenticationService;
import com.backend.organisation.entity.dto.req.AuthenticationRequest;
import com.backend.organisation.entity.dto.res.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticate;

    @PostMapping("/authentication")
    public ResponseEntity<AuthenticationResponse> authentication(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticate.authenticate(request));
    }
}
