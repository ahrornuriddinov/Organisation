package com.backend.organisation.config.securityService;

import com.backend.organisation.entity.User;
import com.backend.organisation.entity.dto.req.AuthenticationRequest;
import com.backend.organisation.entity.dto.res.AuthenticationResponse;
import com.backend.organisation.exceptions.CustomException;
import com.backend.organisation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse authenticate(AuthenticationRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                ));
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new CustomException(400, 5001, "User not found"));
        String token = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }
}
