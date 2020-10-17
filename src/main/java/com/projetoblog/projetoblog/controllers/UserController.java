package com.projetoblog.projetoblog.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("usuario")
    public ResponseEntity<String> Usuario(){
        return ResponseEntity.ok("");
    }

}
