package br.ueg.admin.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UsuarioDto {
    private Long id;

    private String email;

    private String login;

    private String senha;

    private String nome;

    private Set<UsuarioFuncionalidadeDto> funcionalidades;
}
