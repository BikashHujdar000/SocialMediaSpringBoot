package com.example.bikash.Social.Media.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class HomeControllers {


    @GetMapping("/home")
    public String  homeTest(){

         return "This is Home page of Social Media";
    }

}
