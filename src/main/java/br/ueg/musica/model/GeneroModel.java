package br.ueg.musica.model;

import br.ueg.musica.model.MusicaModel;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "TBL_GENERO")
public class GeneroModel implements Serializable {

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    @Column(name = "id_genero", nullable = false)
    private Long id;

    private String nome;

    @OneToMany(mappedBy = "genero", fetch = FetchType.EAGER)
    private Set<MusicaModel> musicas;
}
