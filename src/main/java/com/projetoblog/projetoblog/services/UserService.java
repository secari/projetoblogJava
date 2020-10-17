package com.projetoblog.projetoblog.services;

import com.projetoblog.projetoblog.models.UserModel;
import com.projetoblog.projetoblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public UserModel findByEmail(String email){
        return repository.findByEmail(email);
    }



}
