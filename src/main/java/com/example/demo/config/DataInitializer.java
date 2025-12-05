package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.models.Role;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.utils.UserRoles;

@Configuration
public class DataInitializer {

    @Autowired
    protected RoleRepository roleRepository;

    @Bean
    CommandLineRunner dataLoader() {
        return args -> {
            CreateRoles(UserRoles.values());
        };
    }

    private void CreateRoles(UserRoles[] roles) {
        for (UserRoles role : roles) {
            Role r = new Role();
            r.setName(role);
            if (!roleRepository.existsRoleByName(r.getName()))
                roleRepository.save(r);
        }
    }
}
