package br.ueg.admin.dto;

import lombok.Data;

import java.util.Set;


@Data
public class ModuloDto {
    private Long id;

    private String nome;

    private String mnemonico;

    private Set<FuncionalidadeDto> funcionalidades;
}
