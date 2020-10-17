package com.projetoblog.projetoblog.services;

import com.projetoblog.projetoblog.models.PostModel;
import com.projetoblog.projetoblog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    public Page<PostModel> pg(PageRequest page){

        return repository.findAll(page);
    }

}
