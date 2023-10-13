package br.ueg.musica.model;

import java.time.LocalDate;
import java.util.Date;

import br.ueg.genero.model.GeneroModel;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.Fetch;

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


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_genero", referencedColumnName = "id_genero", nullable = false,
    foreignKey = @ForeignKey(name = "Fk_GENERO_MUSICA"))
    private GeneroModel genero;
}
