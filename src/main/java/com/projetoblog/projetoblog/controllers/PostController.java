package com.projetoblog.projetoblog.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

    @RestController
    public class PostController {
        @GetMapping("post")
        public ResponseEntity<String> Post(){
            return ResponseEntity.ok("");
        }

    }
