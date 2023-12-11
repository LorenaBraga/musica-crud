package br.ueg.musica.model;

import java.time.LocalDate;
import java.util.Set;

import br.ueg.prog.webi.api.interfaces.ISearchFieldData;
import br.ueg.prog.webi.api.model.BaseEntidade;
import br.ueg.prog.webi.api.model.annotation.Searchable;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.domain.Persistable;

@Entity
@Builder
@AllArgsConstructor
@ToString
public @Getter
@Setter
@RequiredArgsConstructor
@Table(name = "TBL_MUSICA")
class Musica extends BaseEntidade <Long> implements Persistable<Long>, ISearchFieldData<Long> {

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Long id;

    @Searchable(label = "Banda")
    private String nomeBanda;

    @Searchable(label = "Música")
    private String nomeMusica;

    @Searchable(label = "Album")
    private String nomeAlbum;

    private LocalDate dataLancamento;

    private Long duracao;

    private Boolean favorito;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_genero", referencedColumnName = "id_genero", nullable = false,
    foreignKey = @ForeignKey(name = "Fk_GENERO_MUSICA"))
    private Genero genero;

    public Musica(String banda, String musica, String album, long i, LocalDate now, boolean favorito, Genero genero) {
        setNomeBanda(banda);
        setNomeMusica(musica);
        setNomeAlbum(album);
        setDuracao(i);
        setDataLancamento(now);
        setGenero(genero);
        setFavorito(favorito);
    }


    @Override
    public String getTabelaNome() {
        return null;
    }

    @Override
    public String getDescription() {
        return this.getNomeMusica();
    }
}
