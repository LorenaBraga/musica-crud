package br.ueg.admin.repository;

import br.ueg.admin.dto.UsuarioDto;
import br.ueg.admin.model.UsuarioModel;

import java.util.List;

public interface UsuarioRepositoryCustom {
    /**
     * Retorna a Lista de {@link br.ueg.admin.dto.UsuarioDto} conforme o login pesquisado.
     *
     * @param login -
     * @return -
     */
    public List<UsuarioDto> findAllByLoginIgnoreCaseContaining(final String login);

    /**
     * Retorna a Lista de {@link UsuarioDto} conforme o filtro pesquisado.
     *
     * @param filtroTO -
     * @return -
     */
    //public List<UsuarioModel> findAllByFiltro(FiltroUsuarioDTO filtroTO);

}
