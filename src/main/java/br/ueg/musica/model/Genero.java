package br.ueg.musica.model;

import br.ueg.prog.webi.api.interfaces.ISearchFieldData;
import br.ueg.prog.webi.api.model.BaseEntidade;
import br.ueg.prog.webi.api.model.annotation.Searchable;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@ToString
public @Getter
@Setter
@RequiredArgsConstructor
@Table(name = "TBL_GENERO")
class Genero extends BaseEntidade<Long> implements ISearchFieldData<Long> {

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    @Column(name = "id_genero", nullable = false)
    private Long id;

    @Searchable(label = "Nome")
    private String nome;

    @OneToMany(mappedBy = "genero", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Builder.Default
    private Set<Musica> musicas = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genero local = (Genero) o;
        return  Objects.equals(getNome(), local.getNome());
    }

    @Override
    public String getDescription() {
        return this.nome;
    }
}
