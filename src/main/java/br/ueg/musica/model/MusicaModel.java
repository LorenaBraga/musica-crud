package br.ueg.musica.model;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Data
@Entity
@Table(name = "TBL_MUSICA")
public class MusicaModel {

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Long id;

    private String nomeBanda;
    private String nomeMusica;
    private String nomeAlbum;
    private LocalDate dataLancamento;
    private Long duracao;

    private Boolean favorito;
}
