package br.ueg.musica.dto;

import br.ueg.musica.model.MusicaModel;
import lombok.Data;

@Data
public class MusicasFavoritasDto {

    private String nomeBanda;

    private Long id;

    private String nomeMusica;

    private String nomeAlbum;

    public MusicasFavoritasDto(MusicaModel musicaModel) {
        setNomeMusica(musicaModel.getNomeMusica());
        setId(musicaModel.getId());
        setNomeBanda(musicaModel.getNomeBanda());
        setNomeAlbum(musicaModel.getNomeAlbum());
    }

    public MusicasFavoritasDto(){}
}
