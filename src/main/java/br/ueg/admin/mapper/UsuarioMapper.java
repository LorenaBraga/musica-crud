package br.ueg.admin.mapper;

import br.ueg.admin.dto.UsuarioDto;
import br.ueg.admin.model.UsuarioModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    public UsuarioDto toDto(UsuarioModel usuarioModel);

    public UsuarioModel toEntity(UsuarioDto usuarioDto);
}
