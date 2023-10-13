package br.ueg.admin.dto;

import br.ueg.admin.model.FuncionalidadeModel;
import br.ueg.admin.model.UsuarioFuncionalidadeModel;
import br.ueg.admin.model.UsuarioModel;
import jakarta.persistence.*;
import org.mapstruct.Mapper;

public class UsuarioFuncionalidadeDto {
    private Long id;

    private UsuarioModel usuario;

    private FuncionalidadeModel funcionalidade;
}
