package com.projetoblog.projetoblog.validators;

import com.projetoblog.projetoblog.models.PostModel;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
public class PostValidator {

    @NotEmpty(message = "Título é obrigatório")
    private String titulo;

    @NotEmpty(message = "Autor é obrigatório")
    private String autor;

    private Long id;

    @NotEmpty(message = "Descrição é obrigatório")
    private String descricao;

    private String conteudo;

    private Timestamp criacao;

    @NotNull(message = "Status é obrigatório")
    private Integer status;

    public PostModel toModel(){
        PostModel item = new PostModel();

        if (id != null && id.intValue() > 0)
            item.setId(id);

        item.setTitulo(titulo);
        item.setStatus(status);
        item.setDescricao(descricao);
        item.setConteudo(conteudo);
        item.setAutores(autor);
        item.setCriacao(criacao);

        return item;
    }
}
