package br.ueg.musica.dto;

import br.ueg.musica.model.MusicaModel;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
public class MusicasFavoritasGeneroDto {

    private String nome;

    private List<MusicasFavoritasDto> musicasfavoritas = new ArrayList<>();

    public MusicasFavoritasGeneroDto(MusicaModel musicaModel) {
        setNome(musicaModel.getGenero().getNome());
    }

    public MusicasFavoritasGeneroDto(){}
}
