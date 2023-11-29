package br.ueg.musica.mapper;

import br.ueg.musica.dto.MusicaDto;
import br.ueg.musica.model.MusicaModel;
import br.ueg.prog.webi.api.mapper.BaseMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MusicaMapper extends BaseMapper<MusicaModel, MusicaDto> {


}
