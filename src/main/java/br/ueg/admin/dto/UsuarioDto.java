package br.ueg.admin.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class UsuarioDto implements Serializable {

    private static final long serialVersionUID = -3145730384721847808L;

    private String id;

    private String email;

    private String login;

    private String senha;

    private String nome;

    private boolean status;

    private Set<UsuarioFuncionalidadeDto> funcionalidades;

    public UsuarioDto(String id, String login) {
        this.id = id;
        this.login = login;
    }
}
