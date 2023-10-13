package br.ueg.admin.mapper;

import br.ueg.admin.dto.FuncionalidadeDto;
import br.ueg.admin.model.FuncionalidadeModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FuncionalidadeMapper {

    public FuncionalidadeDto toEntity(FuncionalidadeModel funcionalidadeModel);

    public FuncionalidadeModel toDto(FuncionalidadeDto funcionalidadeDto);
}
