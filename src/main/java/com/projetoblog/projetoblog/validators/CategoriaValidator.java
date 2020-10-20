package com.projetoblog.projetoblog.validators;

import com.projetoblog.projetoblog.models.CategoriaModel;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
public class CategoriaValidator {

    @NotEmpty(message = "Título é obrigatório")
    private String titulo;

    private Long id;

    private String descricao;

    @NotNull(message = "Status é obrigatório")
    private Integer status;

    private Timestamp criacao;

    public CategoriaModel toModel(){
        CategoriaModel item = new CategoriaModel();

        if (id != null && id.intValue() > 0)
            item.setId(id);

        item.setTitulo(titulo);
        item.setStatus(status);
        item.setDescricao(descricao);
        item.setCriacao(criacao);

        return item;
    }
}