package com.projetoblog.projetoblog.models;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Data
@Entity
@Table(name = "users")
public class UserModel {
    @Basic
    @Column(name = "nome", nullable = false, length = 255)
    private String nome;
    @Basic
    @Column(name = "email", nullable = true, length = 255)
    private String email;
    @Basic
    @Column(name = "password", nullable = false, length = 255)
    private String password;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "criacao", nullable = true)
    private Timestamp criacao;
    @Basic
    @Column(name = "alteracao", nullable = true)
    private Timestamp alteracao;
    @Basic
    @Column(name = "status", nullable = true)
    private Integer status;
//    @OneToMany(mappedBy = "usersByIdUsuario")
//    private Collection<PostModel> postsById;

}
