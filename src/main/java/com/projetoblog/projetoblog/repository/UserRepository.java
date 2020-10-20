package com.projetoblog.projetoblog.repository;

import com.projetoblog.projetoblog.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long>{

   UserModel findByNome(String nome);

   UserModel findByEmail(String email);

   UserModel findByStatus(Integer status);

}