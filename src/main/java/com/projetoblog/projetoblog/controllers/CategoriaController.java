package com.projetoblog.projetoblog.controllers;

import com.projetoblog.projetoblog.models.CategoriaModel;
import com.projetoblog.projetoblog.models.UserModel;
import com.projetoblog.projetoblog.response.Response;
import com.projetoblog.projetoblog.services.CategoriaService;
import com.projetoblog.projetoblog.validators.CategoriaValidator;
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
        "/v1/categoria"
})
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {

    @Autowired
        CategoriaService service;

    @GetMapping("all")
    public ResponseEntity<Response<Page<CategoriaModel>>> all() {

        Response<Page<CategoriaModel>> response = new Response<Page<CategoriaModel>>();

        Page<CategoriaModel> pg = service.pg(PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "titulo")));

        response.setData(pg);
        return ResponseEntity.ok(response);
    }

    @GetMapping("full")
    public ResponseEntity<Response<List<CategoriaModel>>> full(){
        Response<List<CategoriaModel>> response = new Response<List<CategoriaModel>>();

        List<CategoriaModel> pg = service.pg(Sort.by(Sort.Direction.ASC, "titulo"));

        response.setData(pg);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<Response<CategoriaModel>> id(@PathVariable Long id){
        Response<CategoriaModel> response = new Response<CategoriaModel>();

        CategoriaModel pg = service.find(id);

        response.setData(pg);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Response<CategoriaModel>> delete(@PathVariable Long id){
        Response<CategoriaModel> response = new Response<CategoriaModel>();

        CategoriaModel pg = service.find(id);

        service.delete(pg);

        response.setData(pg);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Response<CategoriaModel>> newItem(@Valid @RequestBody CategoriaValidator form){
        Response<CategoriaModel> response = new Response<CategoriaModel>();

        CategoriaModel pg = service.save(form.toModel());

        response.setData(pg);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("{id}")
    public ResponseEntity<Response<Object>> update(@PathVariable Long id, @Valid @RequestBody CategoriaValidator form){
        Response<Object> response = new Response<Object>();

        CategoriaModel vl = service.find(form.getId());

        if(vl == null || id != form.getId() || id == null || form.getId() == null || (id != null && id.intValue() <= 0) || (form.getId() != null && form.getId().intValue() <= 0)){
            response.setData("Post não encontrada");
            return ResponseEntity.badRequest().body(response);
        }

        form.setCriacao(vl.getCriacao());
        CategoriaModel envio = form.toModel();
        CategoriaModel pg = service.update(envio);

        response.setData(pg);
        return ResponseEntity.ok(response);
    }

}