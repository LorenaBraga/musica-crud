package adminmodule.mapper;


import adminmodule.model.enums.StatusAtivoInativo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class StatusAtivoInativoMapper {
    public StatusAtivoInativo asStatusSimNao(boolean valor){
        return StatusAtivoInativo.getById(valor);
    }

    public boolean asBoolean(String valor){
        if(valor == null){
            return false;
        }
        return valor.equalsIgnoreCase(StatusAtivoInativo.ATIVO.toString());
    }
}
