package com.michael.product_management.initializer;

import com.michael.product_management.entity.User;
import com.michael.product_management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        String adminUsername = "admin";
        String adminPassword = "admin123";
        String adminRole = "ROLE_ADMIN";

        if (userRepository.findByUsername(adminUsername).isEmpty()) {
            User admin = User.builder()
                    .username(adminUsername)
                    .password(passwordEncoder.encode(adminPassword))
                    .role(adminRole)
                    .build();

            userRepository.save(admin);
            log.info("Default admin user created: username='admin', password='admin123'");
        } else {
            log.info("Admin user already exists");
        }
    }
}
