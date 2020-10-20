package com.projetoblog.projetoblog.controllers;

import com.projetoblog.projetoblog.models.PostCategoriaModel;
import com.projetoblog.projetoblog.services.PostCategoriaService;

import com.projetoblog.projetoblog.response.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({
        "/v1/postcat"
})

@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostCategoriaController {

    @Autowired
    PostCategoriaService service;

    @GetMapping("all")
    public ResponseEntity<Response<Page<PostCategoriaModel>>> all() {

        Response<Page<PostCategoriaModel>> response = new Response<Page<PostCategoriaModel>>();

        Page<PostCategoriaModel> pg = service.pg(PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "id")));

        response.setData(pg);
        return ResponseEntity.ok(response);
    }
}
