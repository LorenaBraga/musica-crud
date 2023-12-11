package br.ueg.musica.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MusicaDto {
    private String nomeBanda;
    private Long id;
    private String nomeMusica;

    private String nomeAlbum;

    private LocalDate dataLancamento;

    private Long duracao;

    private Boolean favorito;

    private Long id_genero;

    private String nome_genero;
}
