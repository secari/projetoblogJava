package com.projetoblog.projetoblog.controllers;

import com.projetoblog.projetoblog.models.PostModel;
import com.projetoblog.projetoblog.response.Response;
import com.projetoblog.projetoblog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    PostService service;


    @GetMapping("all")
    public ResponseEntity<Response<Page<PostModel>>> all() {

        Response<Page<PostModel>> response = new Response<Page<PostModel>>();

        Page<PostModel> pg = service.pg(PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "titulo")));


        response.setData(pg);
        return ResponseEntity.ok(response);
    }

//    @GetMapping("full")
//    public ResponseEntity<Response<List<PostModel>>> full(){
//
//    }

}
