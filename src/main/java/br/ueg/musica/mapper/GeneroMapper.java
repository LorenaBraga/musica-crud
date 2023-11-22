package br.ueg.musica.mapper;

import br.ueg.musica.dto.GeneroDto;
import br.ueg.musica.model.GeneroModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GeneroMapper {
    public GeneroModel toModelo(GeneroDto generoDto);

    public GeneroDto toGeneroDto(GeneroModel generoModel);

    public List<GeneroDto> toListDto(List<GeneroModel> generoModelList);

}
