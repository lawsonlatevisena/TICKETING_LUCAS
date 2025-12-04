package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.models.Role;
import com.example.demo.repositories.RoleRepository;

@Configuration
public class DataInitializer {

    @Autowired
    protected RoleRepository roleRepository;

    @Bean
    CommandLineRunner dataLoader() {
        return args -> {
            CreateRoles(new String[] { "ROLE_ADMIN_SUPPORT", "ROLE_AGENT_TRAITEMENT", "ROLE_AGENT_SUPPORT",
                    "ROLE_CITOYEN" });
        };
    }

    private void CreateRoles(String[] roles) {
        for (String role : roles) {
            Role r = new Role();
            r.setName(role);
            if (!roleRepository.existsRoleByName(r.getName()))
                roleRepository.save(r);
        }
    }
}
