package com.example.bikash.Social.Media.Response;

import com.example.bikash.Social.Media.Entittes.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInResponse {

    private String jwtToken;
    private User user;

}
