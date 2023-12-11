package br.ueg.musica.dto;

import br.ueg.musica.model.Musica;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MusicasFavoritasGeneroDto {

    private String nome;

    private List<MusicasFavoritasDto> musicasfavoritas = new ArrayList<>();

    public MusicasFavoritasGeneroDto(Musica musicaModel) {
        setNome(musicaModel.getGenero().getNome());
    }

    public MusicasFavoritasGeneroDto(){}
}
