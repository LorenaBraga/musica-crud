package br.ueg.admin.mapper;

import br.ueg.admin.dto.ModuloDto;
import br.ueg.admin.model.ModuloModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ModuloMapper {

    public ModuloDto toEntity(ModuloModel moduloModel);

    public ModuloModel toDto(ModuloDto moduloDto);
}
