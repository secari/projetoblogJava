package com.projetoblog.projetoblog.services;

import com.projetoblog.projetoblog.models.CategoriaModel;
import com.projetoblog.projetoblog.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public Page<CategoriaModel> pg(PageRequest page){
        return repository.findAll(page);
    }

    public List<CategoriaModel> pg(Sort page){
        return repository.findAll(page);
    }

    public CategoriaModel find (Long id){
        return repository.findById(id).get();
    }

    public void delete (CategoriaModel item){
        repository.delete(item);
    }

    public CategoriaModel save(CategoriaModel item){
        item.setCriacao(new Timestamp(new Date().getTime()));
        item.setAlteracao(new Timestamp(new Date().getTime()));

        return repository.save(item);
    }

    public CategoriaModel update(CategoriaModel item){
        item.setAlteracao(new Timestamp(new Date().getTime()));

        return repository.save(item);
    }



}
