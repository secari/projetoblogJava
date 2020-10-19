package com.projetoblog.projetoblog.services;

import com.projetoblog.projetoblog.models.CategoriaModel;
import com.projetoblog.projetoblog.models.PostModel;
import com.projetoblog.projetoblog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    public Page<PostModel> pg(PageRequest page){

        return repository.findAll(page);
    }

    public List<PostModel> pg(Sort page){

        return repository.findAll(page);
    }

    public PostModel find (Long id){

        return repository.findById(id).get();
    }

    public void delete (PostModel item){

        repository.delete(item);
    }

    public PostModel save(PostModel item){
        item.setCriacao(new Timestamp(new Date().getTime()));
        item.setAlteracao(new Timestamp(new Date().getTime()));

        return repository.save(item);
    }

    public PostModel update(PostModel item){
        item.setAlteracao(new Timestamp(new Date().getTime()));

        return repository.save(item);
    }
}