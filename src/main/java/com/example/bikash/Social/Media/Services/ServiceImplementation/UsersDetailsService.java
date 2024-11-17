package com.example.bikash.Social.Media.Services.ServiceImplementation;

import com.example.bikash.Social.Media.Entittes.User;
import com.example.bikash.Social.Media.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class UsersDetailsService   implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = this.userRepository.findByEmail(username).get();



        if (user == null)
        {
            throw new   UsernameNotFoundException("user not found with email : " +username);
        }


        List<GrantedAuthority> authorities = new ArrayList<>();


            return  new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);

    }
}
