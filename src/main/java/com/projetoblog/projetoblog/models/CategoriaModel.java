package com.projetoblog.projetoblog.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Data
@Entity
@Table(name = "categorias")
public class CategoriaModel implements Serializable {
    @Basic
    @Column(name = "titulo", nullable = false, length = 255)
    private String titulo;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "criacao", nullable = true)
    private Timestamp criacao;
    @Basic
    @Column(name = "alteracao", nullable = true)
    private Timestamp alteracao;
    @Basic
    @Column(name = "descricao", nullable = true, length = -1)
    private String descricao;
    @Basic
    @Column(name = "status", nullable = true)
    private Integer status;
   // @OneToMany(mappedBy = "categoriasByIdCategoria")
   // private Collection<PostCategoriaModel> postCategoriasById;

}