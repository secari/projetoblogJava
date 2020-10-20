package com.projetoblog.projetoblog.controllers;

import com.projetoblog.projetoblog.models.UserModel;
import com.projetoblog.projetoblog.response.Response;
import com.projetoblog.projetoblog.services.UserService;
import com.projetoblog.projetoblog.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping({
        "/v1/user"
})
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    @Autowired
        UserService service;

    @GetMapping("all")
    public ResponseEntity<Response<Page<UserModel>>> all() {

        Response<Page<UserModel>> response = new Response<Page<UserModel>>();

        Page<UserModel> pg = service.pg(PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "nome")));

        response.setData(pg);
        return ResponseEntity.ok(response);
    }

    @GetMapping("full")
    public ResponseEntity<Response<List<UserModel>>> full(){
        Response<List<UserModel>> response = new Response<List<UserModel>>();

        List<UserModel> pg = service.pg(Sort.by(Sort.Direction.ASC, "nome"));

        response.setData(pg);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<Response<UserModel>> id(@PathVariable Long id){
        Response<UserModel> response = new Response<UserModel>();

        UserModel pg = service.find(id);

        response.setData(pg);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Response<UserModel>> delete(@PathVariable Long id){
        Response<UserModel> response = new Response<UserModel>();

        UserModel pg = service.find(id);

        service.delete(pg);

        response.setData(pg);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Response<UserModel>> newItem(@Valid @RequestBody UserValidator form){
        Response<UserModel> response = new Response<UserModel>();

        UserModel pg = service.save(form.toModel());

        response.setData(pg);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("{id}")
    public ResponseEntity<Response<Object>> update(@PathVariable Long id, @Valid @RequestBody UserValidator form){
        Response<Object> response = new Response<Object>();

        UserModel vl = service.find(form.getId());

        if(vl == null || id != form.getId() || id == null || form.getId() == null || (id != null && id.intValue() <= 0) || (form.getId() != null && form.getId().intValue() <= 0)){
            response.setData("Usuário não encontrado");
            return ResponseEntity.badRequest().body(response);
        }

        form.setCriacao(vl.getCriacao());
        UserModel envio = form.toModel();
        UserModel pg = service.update(envio);

        response.setData(pg);
        return ResponseEntity.ok(response);
    }
}