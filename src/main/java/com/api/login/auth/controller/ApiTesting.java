package com.api.login.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testing")
public class ApiTesting {

    @GetMapping("")
    public String testing(){
        return "Api online !";
    }
    
}
