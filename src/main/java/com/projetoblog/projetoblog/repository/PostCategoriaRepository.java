package com.projetoblog.projetoblog.repository;

import com.projetoblog.projetoblog.models.PostCategoriaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostCategoriaRepository extends JpaRepository<PostCategoriaModel, Long> {

    @Query("SELECT a FROM PostCategoriaModel a WHERE a.idPost = :id_post")
    List<PostCategoriaModel> findByIdPost(Long id_post);

    @Query("SELECT a FROM PostCategoriaModel a WHERE a.idCategoria = :id_categoria")
    List<PostCategoriaModel> findByIdCategoria(Long id_categoria);
}