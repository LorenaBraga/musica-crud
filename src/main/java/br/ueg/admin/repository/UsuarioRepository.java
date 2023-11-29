package br.ueg.admin.repository;

import br.ueg.admin.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuarioRepository extends UsuarioRepositoryCustom, JpaRepository<UsuarioModel, Long> {

    /**
     * Retorna a instância do {@link UsuarioModel} conforme o 'login' informado.
     *
     * @param login
     * @return
     */
    public UsuarioModel findByLogin(final String login);

    /**
     * Retorna a instância do {@link UsuarioModel} conforme o 'login' informado
     * e que não tenha o 'id' informado.
     *
     * @param login
     * @param id
     * @return
     */
    @Query(" SELECT usuario FROM UsuarioModel usuario "
            + " WHERE usuario.login = :login AND (:id IS NULL OR usuario.id != :id)")
    public UsuarioModel findByLoginAndNotId(@Param("login") final String login, @Param("id") final Long id);

    /**
     * Retorna uma instância de {@link UsuarioModel} conforme o 'id' informado.
     *
     * @param id
     * @return
     */
    @Query(" SELECT usuario FROM UsuarioModel usuario "
            + " WHERE usuario.id = :id ")
    public Optional<UsuarioModel> findByIdFetch(@Param("id") final Long id);


    /**
     * Retorna o total de Usuários encontrados na base de dados conforme o Login
     * informado.
     *
     * @param login login do usuário
     * @return
     */
    public Long countByLogin(final String login);
}
