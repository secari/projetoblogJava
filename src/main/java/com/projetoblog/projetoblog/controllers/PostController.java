package com.projetoblog.projetoblog.controllers;
import com.projetoblog.projetoblog.models.PostCategoriaModel;
import com.projetoblog.projetoblog.models.PostModel;
import com.projetoblog.projetoblog.models.UserModel;
import com.projetoblog.projetoblog.response.Response;
import com.projetoblog.projetoblog.security.JwtUser;
import com.projetoblog.projetoblog.services.PostCategoriaService;
import com.projetoblog.projetoblog.services.PostService;
import com.projetoblog.projetoblog.validators.PostValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping({
        "/v1/post"
})

@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostController {

    @Autowired
    PostService service;

    @Autowired
    PostCategoriaService serviceBelongs;

    @Autowired
    EntityManager entityManager;

    @GetMapping("all")
    public ResponseEntity<Response<Page<PostModel>>> all() {

        Response<Page<PostModel>> response = new Response<Page<PostModel>>();

        Page<PostModel> pg = service.pg(PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "titulo")));


        response.setData(pg);
        return ResponseEntity.ok(response);
    }

    @GetMapping("full")
    public ResponseEntity<Response<List<PostModel>>> full(){
        Response<List<PostModel>> response = new  Response<List<PostModel>>();

        List<PostModel> pg = service.pg(Sort.by(Sort.Direction.ASC, "titulo"));

        response.setData(pg);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<Response<PostModel>> id(@PathVariable Long id){
        Response<PostModel> response = new Response<PostModel>();

        PostModel pg = service.find(id);

        response.setData(pg);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Response<PostModel>> delete(@PathVariable Long id){
        Response<PostModel> response = new Response<PostModel>();

        PostModel pg = service.find(id);

        service.delete(pg);

        response.setData(pg);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Response<PostModel>> newItem(@AuthenticationPrincipal JwtUser user, @Valid @RequestBody PostValidator form){
        Response<PostModel> response = new Response<PostModel>();
        form.setIdUsuario(user.getId());
        PostModel pg = service.save(form.toModel());

        if(form.getIdsCategorias() != null && form.getIdsCategorias().size() > 0){
            form.getIdsCategorias().forEach(i -> {
                PostCategoriaModel relacao = new PostCategoriaModel();

                relacao.setIdPost(pg.getId());
                relacao.setIdCategoria(i);

                serviceBelongs.save(relacao);
            });
        }


        response.setData(pg);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("{id}")
    public ResponseEntity<Response<Object>> update(@AuthenticationPrincipal JwtUser user, @PathVariable Long id, @Valid @RequestBody PostValidator form){
        Response<Object> response = new Response<Object>();

        PostModel vl = service.find(form.getId());

        if(vl == null || id != form.getId() || id == null || form.getId() == null || (id != null && id.intValue() <= 0) || (form.getId() != null && form.getId().intValue() <= 0)){
            response.setData("Post não encontrada");
            return ResponseEntity.badRequest().body(response);
        }



        form.setCriacao(vl.getCriacao());
        PostModel envio = form.toModel();
        envio.setIdUsuario(user.getId());
        PostModel pg = service.update(envio);

        if(form.getIdsCategorias() != null && form.getIdsCategorias().size() > 0){
            serviceBelongs.findIdPost(pg.getId()).forEach(j ->{
                serviceBelongs.delete(j);
            });

            form.getIdsCategorias().forEach(i -> {
                PostCategoriaModel relacao = new PostCategoriaModel();

                relacao.setIdPost(form.getId());
                relacao.setIdCategoria(i);

                serviceBelongs.save(relacao);
            });
        }

        entityManager.clear();

        PostModel pg2 = service.find(form.getId());

        response.setData(pg2);
        return ResponseEntity.ok(response);
    }
}