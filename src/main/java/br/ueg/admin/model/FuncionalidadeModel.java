package br.ueg.admin.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Entity
@Table(name = "TBL_FUNCIONALIDADE")
@Data
public class FuncionalidadeModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_funcionalidade", nullable = false)
    private Long id;

    @Column(name = "nome_funcionalidade", length = 200, nullable = false)
    private String nome;

    @Column(name = "mnemonico_funcionalidade", length = 40, nullable = false)
    private String mnemonico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_modulo_sistema", referencedColumnName = "id_modulo_sistema", nullable = false,
            foreignKey = @ForeignKey(name = "FK_FUNCIONALIDADE_MODULO"))
    private ModuloModel moduloSistema;
}
