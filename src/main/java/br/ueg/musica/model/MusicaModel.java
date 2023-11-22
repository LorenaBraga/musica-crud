package br.ueg.musica.model;

import java.time.LocalDate;

import br.ueg.prog.webi.api.model.BaseEntidade;
import br.ueg.prog.webi.api.model.IEntidade;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TBL_MUSICA")
public class MusicaModel extends BaseEntidade <Long> {

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

    @Override
    public String getTabelaNome() {
        return null;
    }
}
