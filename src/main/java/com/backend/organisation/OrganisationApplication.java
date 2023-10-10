package com.backend.organisation;

import com.backend.organisation.entity.enums.UserRole;
import com.backend.organisation.service.AppUserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Library;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Library APIS",version = "1.0",description = "Library Management Apis."))
public class OrganisationApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrganisationApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
