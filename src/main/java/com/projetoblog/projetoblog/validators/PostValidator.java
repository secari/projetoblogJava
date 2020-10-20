package com.projetoblog.projetoblog.validators;

import com.projetoblog.projetoblog.models.PostModel;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@Data
public class PostValidator {

    @NotEmpty(message = "Título é obrigatório")
    private String titulo;

    @NotEmpty(message = "Autor é obrigatório")
    private String autor;

    private Long id;

    private String descricao;

    private String conteudo;

    private Timestamp criacao;

    @NotNull(message = "Status é obrigatório")
    private Integer status;

    private Long idUsuario;

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
        item.setIdUsuario(idUsuario);

        return item;
    }

    @NotEmpty(message = "Id de categoria é obrigatório")
    private List<Long> idsCategorias;


}