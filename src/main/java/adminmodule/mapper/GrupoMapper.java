package adminmodule.mapper;


import adminmodule.dto.model.GrupoDTO;
import adminmodule.model.Grupo;
import org.mapstruct.Mapper;

/**
 * Classe adapter referente a entidade {@link Grupo}.
 *
 * @author UEG
 */
@Mapper(componentModel = "spring", uses = { StatusAtivoInativoMapper.class, SimNaoMapper.class, FuncionalidadeMapper.class})
public interface GrupoMapper {
    /**
     * Converte a entidade {@link Grupo} em DTO {@link GrupoDTO}
     *
     * @param grupo
     * @return
     */

    public GrupoDTO toDTO(Grupo grupo);

    /**
     * Converte o DTO {@link GrupoDTO} para entidade {@link Grupo}
     *
     * @param grupoDTO
     * @return
     */

    public Grupo toEntity(GrupoDTO grupoDTO);
}
