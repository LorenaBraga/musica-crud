package br.ueg.musica.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MusicaDto {
    private String nomeBanda;
    private Long id;
    private String nomeMusica;

    private String nomeAlbum;

    private LocalDate dataLancamento;

    private Long duracao;

    private Boolean favorito;

    private GeneroDto genero;
}
