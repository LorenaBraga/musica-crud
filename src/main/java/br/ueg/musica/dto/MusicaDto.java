package br.ueg.musica.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class MusicaDto {
    private String nomeBanda;
    private Long id;
    private String nomeMusica;

    private String nomeAlbum;

    private LocalDate dataLancamento;

    private Long duracao;

    private Boolean favorito;
}
