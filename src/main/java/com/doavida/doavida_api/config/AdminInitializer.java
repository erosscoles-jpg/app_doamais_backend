package com.doavida.doavida_api.config;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.doavida.doavida_api.model.entities.Employee;
import com.doavida.doavida_api.model.entities.enums.EmployeeFunction;
import com.doavida.doavida_api.repositories.EmployeeRepository;

@Component
public class AdminInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(AdminInitializer.class);
    
    private final EmployeeRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final String email;
    private final String password;
    
    public AdminInitializer(EmployeeRepository repository, PasswordEncoder passwordEncoder,
            @Value("${app.admin.email:admin@doamais.com}") String email,
            @Value("${app.admin.password:}") String password) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.email = email;
        this.password = password;
    }
    
    @Override
    public void run(String... args) {
        if (repository.count() > 0) {
            return;
        }
        String rawPassword = password.isBlank() ? UUID.randomUUID().toString() : password;
        Employee admin = new Employee(null, "Administrador", email,
                passwordEncoder.encode(rawPassword), EmployeeFunction.ADMIN);
        repository.save(admin);
        if (password.isBlank()) {
            log.warn("Administrador criado: {} | senha gerada: {}", email, rawPassword);
        }
    }
}
