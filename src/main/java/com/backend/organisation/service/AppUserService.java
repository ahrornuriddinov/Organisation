package com.backend.organisation.service;

import com.backend.organisation.entity.User;
import com.backend.organisation.entity.enums.UserRole;
import com.backend.organisation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AppUserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()){
            throw new UsernameNotFoundException(username);
        }
        return new User(
                user.get().getUsername(),
                user.get().getPassword(),
                user.get().getUserRole()
        );
    }


    public void addUserDatabase(String username, String password,UserRole userRole) {
        String encode = passwordEncoder.encode(password);
        User user = new User(username, encode, userRole);
        userRepository.save(user);
    }
}
