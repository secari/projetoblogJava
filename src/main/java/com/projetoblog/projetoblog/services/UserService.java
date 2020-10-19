package com.projetoblog.projetoblog.services;

import com.projetoblog.projetoblog.models.UserModel;
import com.projetoblog.projetoblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public UserModel findByEmail(String email){

        return repository.findByEmail(email);
    }

    public Page<UserModel> pg(PageRequest page){

        return repository.findAll(page);
    }

    public List<UserModel> pg(Sort page){

        return repository.findAll(page);
    }

    public UserModel find (Long id){

        return repository.findById(id).get();
    }

    public void delete (UserModel item){

        repository.delete(item);
    }

    public UserModel save(UserModel item){
        item.setCriacao(new Timestamp(new Date().getTime()));
        item.setAlteracao(new Timestamp(new Date().getTime()));

        return repository.save(item);
    }

    public UserModel update(UserModel item){
        item.setAlteracao(new Timestamp(new Date().getTime()));

        return repository.save(item);
    }
}