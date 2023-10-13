package br.ueg.admin.mapper;

import br.ueg.admin.dto.UsuarioFuncionalidadeDto;
import br.ueg.admin.model.UsuarioFuncionalidadeModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioFuncionalidadeMapper {

    public UsuarioFuncionalidadeDto toEntity(UsuarioFuncionalidadeModel usuarioFuncionalidadeModel);

    public UsuarioFuncionalidadeModel toDto(UsuarioFuncionalidadeDto usuarioFuncionalidadeDto);
}
