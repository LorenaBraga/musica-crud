package br.ueg.admin.repository.impl;

import br.ueg.admin.dto.UsuarioDto;
import br.ueg.admin.repository.UsuarioRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<UsuarioDto> findAllByLoginIgnoreCaseContaining(String login) {
        StringBuilder jpql = new StringBuilder();
        jpql.append(" SELECT new UsuarioDTO(usuario.id , usuario.login)");
        jpql.append(" FROM Usuario usuario");
        jpql.append(" WHERE usuario.login LIKE ('%' + :login + '%')");

        TypedQuery<UsuarioDto> query = entityManager.createQuery(jpql.toString(), UsuarioDto.class);
        query.setParameter("login", login);
        return query.getResultList();    }
}