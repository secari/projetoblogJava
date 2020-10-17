package com.projetoblog.projetoblog.repository;

import com.projetoblog.projetoblog.models.CategoriaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaModel, Long> {

    CategoriaModel findByTitulo(String titulo);
}
