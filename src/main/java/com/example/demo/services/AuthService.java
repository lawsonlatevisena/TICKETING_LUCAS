package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.LoginUserRequestDto;
import com.example.demo.dto.RegisterUserRequestDto;
import com.example.demo.dto.UserInfoDto;
import com.example.demo.exceptions.ConflictException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.repositories.AuthRepository;
import com.example.demo.repositories.RoleRepository;

@Service
public class AuthService {

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    private UserInfoDto converUserToUserInfoDto(User user) {
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setFirstname(user.getFirstName());
        userInfoDto.setLastname(user.getLastName());
        userInfoDto.setEmail(user.getEmail());
        userInfoDto.setPhone(user.getPhone());
        userInfoDto.setAddress(user.getAddress());
        userInfoDto.setRoles(user.getRoles().stream().map(Role::getName).toArray(String[]::new));
        return userInfoDto;
    }

    public UserInfoDto register(RegisterUserRequestDto registerUserRequestDto) {

        if (authRepository.existsByEmail(registerUserRequestDto.getEmail()))
            throw new ConflictException("Email already exists");

        // On cree une nouvelle instance de User
        User userToSave = new User(registerUserRequestDto.getEmail(), registerUserRequestDto.getFirstName(),
                registerUserRequestDto.getLastName(), passwordEncoder.encode(registerUserRequestDto.getPassword()),
                registerUserRequestDto.getAddress(), registerUserRequestDto.getPhone());

        // On lui attribut le role client par default
        Role userRole = roleRepository.findByName("ROLE_CITOYEN");
        userToSave.getRoles().add(userRole);

        User savedUser = authRepository.save(userToSave);
        return converUserToUserInfoDto(savedUser);
    }

    public UserInfoDto login(LoginUserRequestDto loginUserRequestDto) {
        User u = authRepository.findByEmail(loginUserRequestDto.getEmail());

        if (u == null || !passwordEncoder.matches(loginUserRequestDto.getPassword(), u.getPassword()))
            throw new NotFoundException("Invalide Creditentials");

        return converUserToUserInfoDto(u);
    }

}
