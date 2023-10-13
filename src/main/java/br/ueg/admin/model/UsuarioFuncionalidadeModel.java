package br.ueg.admin.model;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "TBL_USUARIO_FUNCIONALIDADE")
@Data
@Entity
public class UsuarioFuncionalidadeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_usuario_funcionalidade", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", nullable = false,
            foreignKey = @ForeignKey(name = "FK_USUARIO_FUNCIONALIDADE_GRUPO"))
    private UsuarioModel usuario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_funcionalidade", referencedColumnName = "id_funcionalidade", nullable = false,
            foreignKey = @ForeignKey(name = "FK_USUARIO_FUN_FUNCIONALIDADE"))
    private FuncionalidadeModel funcionalidade;
}
