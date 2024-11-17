package com.example.bikash.Social.Media.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInRequest {

    @NotEmpty(message = "Name is required")
    private String firstName;

    @NotEmpty(message = "Name is required")
    private String lastName;

    @Email(message = "Enter a valid email")
    @Pattern(regexp = "^[\\w-\\.]+@[\\w-]+\\.[a-zA-Z]{2,4}$", message = "Email format is invalid")
    private String email;

    @NotEmpty
    @Size(min = 5, message = "Password should be a minimum of 5 characters")
    private String password;



}
