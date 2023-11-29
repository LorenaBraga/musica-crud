package br.ueg.musica.mapper;

import br.ueg.musica.dto.GeneroDto;
import br.ueg.musica.model.GeneroModel;
import br.ueg.prog.webi.api.mapper.BaseMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GeneroMapper extends BaseMapper<GeneroModel, GeneroDto> {

}
