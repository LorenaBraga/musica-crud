package br.ueg.admin.model;

import br.ueg.admin.model.enums.StatusAtivoInativo;
import br.ueg.admin.model.enums.StatusAtivoInativoConverter;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@Table(name = "TBL_USUARIO")
public class UsuarioModel implements Serializable {

    private static final long serialVersionUID = -8899975090870463525L;

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    @Column(name = "id_usuario")
    private Long id;

    @Column(name = "email", length = 75, nullable = false)
    private String email;

    @Column(name = "login", length = 20, nullable = false)
    private String login;

    @Column(name = "senha", length = 255, nullable = false)
    private String senha;

    @Column(name = "nome", length = 65, nullable = false)
    private String nome;

    @Convert(converter = StatusAtivoInativoConverter.class)
    @Column(name = "status", nullable = false, length = 1)
    private StatusAtivoInativo status;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UsuarioFuncionalidadeModel> usuarioFuncinalidades;

    @Transient
    public boolean isStatusAtivo() {
        return status != null && StatusAtivoInativo.ATIVO.getId().equals(status.getId());
    }
}
