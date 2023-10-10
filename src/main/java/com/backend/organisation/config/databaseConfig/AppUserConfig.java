package com.backend.organisation.config.databaseConfig;

import com.backend.organisation.entity.enums.UserRole;
import com.backend.organisation.service.AppUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
@Slf4j
public class AppUserConfig {
    private final AppUserService userService;
    @Bean
    public void addUserDatabase(){
        userService.addUserDatabase("admin","2611", UserRole.ROLE_ADMIN);
        userService.addUserDatabase("superadmin","565",UserRole.ROLE_SUPERADMIN);
        log.info("Security uchun default qo'shilgan userlar");
        log.info("username=admin password=2611 userRole=ROLE_ADMIN");
        log.info("username=superadmin password=565 userRole=ROLE_SUPERADMIN");
    }


}
