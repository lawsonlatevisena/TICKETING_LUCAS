package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class RegisterUserRequestDto {

    @Email(message = "Email is not valid")
    private String email;

    @NotBlank
    @Min(value = 2, message = "First name must be at least 2 characters long")
    private String firstName;

    @Min(value = 2, message = "Last name must be at least 2 characters long")
    private String lastName;

    @NotBlank
    @Min(value = 6, message = "Password must be at least 6 characters long")
    @Max(value = 20, message = "Password must be at most 20 characters long")
    private String password;

    private String phone;
    private String address;

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

}
