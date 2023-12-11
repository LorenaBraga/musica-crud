/*
 * UsuarioRepositoryCustom.java
 * Copyright (c) UEG.
 *
 *
 *
 *
 */
package adminmodule.repository;

import adminmodule.dto.filtros.FiltroUsuarioDTO;
import adminmodule.dto.model.UsuarioDTO;
import adminmodule.model.Usuario;

import java.util.List;

/**
 * Classe de persistência referente a entidade {@link Usuario}.
 * 
 * @author UEG
 */
public interface UsuarioRepositoryCustom {

	/**
	 * Retorna a Lista de {@link UsuarioDTO} conforme o login pesquisado.
	 * 
	 * @param login -
	 * @return -
	 */
	public List<UsuarioDTO> findAllByLoginIgnoreCaseContaining(final String login);

	/**
	 * Retorna a Lista de {@link UsuarioDTO} conforme o filtro pesquisado.
	 * 
	 * @param filtroTO -
	 * @return -
	 */
	public List<Usuario> findAllByFiltro(FiltroUsuarioDTO filtroTO);

}
