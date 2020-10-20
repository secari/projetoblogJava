package com.projetoblog.projetoblog.services;

import com.projetoblog.projetoblog.models.PostCategoriaModel;
import com.projetoblog.projetoblog.repository.PostCategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostCategoriaService {

    @Autowired
    private PostCategoriaRepository repository;

    public Page<PostCategoriaModel> pg(PageRequest page){

        return repository.findAll(page);
    }

    public List<PostCategoriaModel> pg(Sort page){

        return repository.findAll(page);
    }

    public void delete (PostCategoriaModel item){

        repository.delete(item);
    }

    public PostCategoriaModel save(PostCategoriaModel item){

        return repository.save(item);
    }

    //Busca por ID's
    public List<PostCategoriaModel> findIdPost (Long idPost){

        return repository.findByIdPost(idPost);
    }

    public List<PostCategoriaModel> findIdCategoria (Long idCategoria){

        return repository.findByIdCategoria(idCategoria);
    }

}