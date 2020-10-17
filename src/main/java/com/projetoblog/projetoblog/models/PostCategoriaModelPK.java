package com.projetoblog.projetoblog.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Data
public class PostCategoriaModelPK implements Serializable {
    @Column(name = "id_post", nullable = false)
    @Id
    private Integer idPost;
    @Column(name = "id_categoria", nullable = false)
    @Id
    private Integer idCategoria;

}
