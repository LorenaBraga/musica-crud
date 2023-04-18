package br.ueg.musica.mapper;

import br.ueg.musica.dto.MusicaDto;
import br.ueg.musica.model.MusicaModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MusicaMapper {


    public MusicaModel toModelo(MusicaDto musicaDto);

    public MusicaDto toMusicaDto(MusicaModel musicaModel);

    public List<MusicaDto> toListDto(List<MusicaModel> musicaModelList);

}
