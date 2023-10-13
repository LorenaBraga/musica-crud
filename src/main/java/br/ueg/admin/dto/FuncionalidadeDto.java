package br.ueg.admin.dto;

import lombok.Data;

@Data
public class FuncionalidadeDto {

    private Long id;

    private String nome;

    private String mnemonico;

    private ModuloDto moduloSistema;
}
