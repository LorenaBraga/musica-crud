package br.ueg.musica.dto;

import br.ueg.musica.model.Musica;
import lombok.Data;

@Data
public class MusicasFavoritasDto {

    private String nomeBanda;

    private Long id;

    private String nomeMusica;

    private String nomeAlbum;

    public MusicasFavoritasDto(Musica musicaModel) {
        setNomeMusica(musicaModel.getNomeMusica());
        setId(musicaModel.getId());
        setNomeBanda(musicaModel.getNomeBanda());
        setNomeAlbum(musicaModel.getNomeAlbum());
    }

    public MusicasFavoritasDto(){}
}
