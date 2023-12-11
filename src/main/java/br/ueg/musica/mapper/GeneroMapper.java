package br.ueg.musica.mapper;

import br.ueg.musica.dto.GeneroDto;
import br.ueg.musica.model.Genero;
import br.ueg.prog.webi.api.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;

@Mapper(componentModel = "spring")
public interface GeneroMapper extends BaseMapper<Genero, GeneroDto> {

}
