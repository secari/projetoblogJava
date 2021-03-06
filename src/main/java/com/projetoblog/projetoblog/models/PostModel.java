package com.projetoblog.projetoblog.models;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Data
@Entity
@Table(name = "posts")
public class PostModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "titulo", nullable = true, length = 255)
    private String titulo;
    @Basic
    @Column(name = "conteudo", nullable = false, length = -1)
    private String conteudo;
    @Basic
    @Column(name = "autores", nullable = true, length = 100)
    private String autores;
    @Basic
    @Column(name = "criacao", nullable = true)
    private Timestamp criacao;
    @Basic
    @Column(name = "alteracao", nullable = true)
    private Timestamp alteracao;
    @Basic
    @Column(name = "status", nullable = true)
    private Integer status;
    @Basic
    @Column(name = "descricao", nullable = true, length = -1)
    private String descricao;

    @Basic
    @Column(name = "id_usuario", nullable = true, length = -1)
    private Long idUsuario;

    @ManyToMany
    @JoinTable(name="post_categoria",
            joinColumns={@JoinColumn(name="id_post")},
            inverseJoinColumns={@JoinColumn(name="id_categoria")})
    private Collection<CategoriaModel> categorias;

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", nullable = false, updatable = false, insertable = false)
    private UserModel usuario;

}
