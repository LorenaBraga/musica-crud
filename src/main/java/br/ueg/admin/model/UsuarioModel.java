package br.ueg.admin.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "TBL_USUARIO")
public class UsuarioModel {

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

    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UsuarioFuncionalidadeModel> usuarioFuncinalidades;
}
