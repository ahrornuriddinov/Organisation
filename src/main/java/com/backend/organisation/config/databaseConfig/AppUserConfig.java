package com.backend.organisation.config.databaseConfig;

import com.backend.organisation.entity.enums.UserRole;
import com.backend.organisation.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AppUserConfig {
    private final AppUserService userService;
    @Bean
    public void addUserDatabase(){
        userService.addUserDatabase("admin","2611", UserRole.ROLE_ADMIN);
        userService.addUserDatabase("superadmin","565",UserRole.ROLE_SUPERADMIN);
    }

}
