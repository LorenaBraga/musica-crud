package br.ueg.musica.mapper;

import br.ueg.musica.dto.MusicaDto;
import br.ueg.musica.model.Musica;
import br.ueg.prog.webi.api.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MusicaMapper extends BaseMapper<Musica, MusicaDto> {

    @Override
    @Mapping(source = "genero.id" , target = "id_genero")
    @Mapping(source = "genero.nome" , target = "nome_genero")
    MusicaDto toDTO(Musica modelo);

    @Override
    @Mapping(source = "id_genero" , target = "genero.id")
    Musica toModelo(MusicaDto musicaDto);
}
