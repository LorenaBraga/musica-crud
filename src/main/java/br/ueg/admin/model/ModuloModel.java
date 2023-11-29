package br.ueg.admin.model;

import br.ueg.admin.model.enums.StatusAtivoInativo;
import br.ueg.admin.model.enums.StatusAtivoInativoConverter;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "TBL_MODULO_SISTEMA")
@Data
public class ModuloModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_modulo_sistema", nullable = false)
    private Long id;

    @Column(name = "nome_modulo", length = 200, nullable = false)
    private String nome;

    @Column(name = "mnemonico_modulo", length = 40, nullable = false)
    private String mnemonico;

    @Convert(converter = StatusAtivoInativoConverter.class)
    @Column(name = "status", nullable = false, length = 1)
    private StatusAtivoInativo status;

    @OneToMany(mappedBy = "moduloSistema", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<FuncionalidadeModel> funcionalidades;
}
