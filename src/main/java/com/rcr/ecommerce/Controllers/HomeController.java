package com.rcr.ecommerce.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class HomeController {
    @GetMapping("/home")
    private String homePage(){
        return "This is the home page of mobile ecommerce website";
    }
}
