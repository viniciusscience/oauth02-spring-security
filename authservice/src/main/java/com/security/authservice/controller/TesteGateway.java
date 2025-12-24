package com.security.authservice.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
public class TesteGateway {


    @GetMapping
    public String getTeste(){
        return "TESTE OK";
    }
}
