package com.projetoblog.projetoblog.validators;

import com.projetoblog.projetoblog.models.UserModel;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
public class UserValidator {
    @NotEmpty(message = "Nome é obrigatório")
    private String nome;

    private Long id;

    @NotEmpty(message = "Email é obrigatório")
    private String email;

    @NotEmpty(message = "Senha é obrigatório")
    private String password;

    @NotNull(message = "Status é obrigatório")
    private Integer status;

    private Timestamp criacao;

    public UserModel toModel(){
        UserModel item = new UserModel();

        if (id != null && id.intValue() > 0)
            item.setId(id);

        item.setNome(nome);
        item.setEmail(email);
        item.setPassword(password);
        item.setStatus(status);
        item.setCriacao(criacao);

        return item;
    }
}