package com.projetoblog.projetoblog.models;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "post_categoria")
@IdClass(PostCategoriaModelPK.class)
public class PostCategoriaModel {

    @Id
    @Column(name = "id_post", nullable = false)
    private Long idPost;

    @Id
    @Column(name = "id_categoria", nullable = false)
    private Long idCategoria;

//    @ManyToOne
//    @JoinColumn(name = "id_post", referencedColumnName = "id", nullable = false)
//    private PostModel postsByIdPost;
//    @ManyToOne
//    @JoinColumn(name = "id_categoria", referencedColumnName = "id", nullable = false)
//    private CategoriaModel categoriasByIdCategoria;

}
