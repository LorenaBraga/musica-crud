package br.ueg.admin.mapper;

import br.ueg.admin.dto.UsuarioDto;
import br.ueg.admin.model.UsuarioModel;
import br.ueg.prog.webi.api.dto.CredencialDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { StatusAtivoInativoMapper.class, SimNaoMapper.class, UsuarioMapper.class })
public interface UsuarioMapper {
    /**
     * Converte a entidade {@link UsuarioModel} em DTO {@link UsuarioDto}
     *
     * @param usuario
     * @return
     */

    public UsuarioDto toDTO(UsuarioModel usuario);

    /**
     * Converte o DTO {@link UsuarioDto} para entidade {@link UsuarioModel}
     *
     * @param usuarioDTO
     * @return
     */
    public UsuarioModel toEntity(UsuarioDto usuarioDTO);

    public CredencialDTO toCredentialDTO(UsuarioModel usuario);

    public UsuarioModel toUsuarioFromCredentialDTO(CredencialDTO credencialDTO);
}
