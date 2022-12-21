package co.develhope.Login.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("verify")
public class LoginTestController {
    @GetMapping
    public String verify(){
        return "You are logged in succesfully!";
    }
}
