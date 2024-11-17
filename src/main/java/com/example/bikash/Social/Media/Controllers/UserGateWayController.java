package com.example.bikash.Social.Media.Controllers;

import com.example.bikash.Social.Media.Configuration.JWT.JwtUtils;
import com.example.bikash.Social.Media.Entittes.User;
import com.example.bikash.Social.Media.Repositories.UserRepository;
import com.example.bikash.Social.Media.Request.LoginRequest;
import com.example.bikash.Social.Media.Response.SignInResponse;
import com.example.bikash.Social.Media.Services.ServiceImplementation.UserServiceImpl;
import com.example.bikash.Social.Media.Services.ServiceImplementation.UsersDetailsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserGateWayController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UsersDetailsService usersDetailsService;

    @Autowired
    private JwtUtils jwtUtils;


    @Autowired
    private UserRepository userRepository;


    @PostMapping("/register")
    public ResponseEntity<User> signUpUser(@RequestBody User user) {


        User savedUser = this.userService.registerUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("")
    public String homeTest() {

        return "This is Home page of Social Media";
    }


    // lets create for login
    @PostMapping("/login")
    public ResponseEntity<SignInResponse> login(@RequestBody LoginRequest loginRequest) {

        // authentication set

        try {
            this.authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        } catch (AuthenticationException e) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }


        // okay not authentication manager have my login email and password

        UserDetails userDetails;

        try {
            userDetails = this.usersDetailsService.loadUserByUsername(loginRequest.getEmail());


        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }


        // aab agar user milgaya toh kya kar vai tu nikal aab token

        String token = this.jwtUtils.generateToken(userDetails.getUsername());




         User logedInUser =  this.userRepository.findByEmail(userDetails.getUsername()).get();

        System.out.println(logedInUser);
        // now return what you want

        SignInResponse response = new SignInResponse();

        response.setJwtToken(token);
        response.setUser(logedInUser);


        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);


    }


}


