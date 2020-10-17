package com.projetoblog.projetoblog.repository;

import com.projetoblog.projetoblog.models.PostModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostModel, Long> {

    PostModel findByTitulo(String titulo);
}
