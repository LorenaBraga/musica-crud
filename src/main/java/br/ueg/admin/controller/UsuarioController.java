package br.ueg.admin.controller;

import br.ueg.admin.dto.UsuarioDto;
import br.ueg.admin.mapper.UsuarioMapper;
import br.ueg.admin.service.UsuarioService;
import br.ueg.admin.model.UsuarioModel;
import br.ueg.prog.webi.api.controller.AbstractController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${app.api.base}/usuarios")
public class UsuarioController extends AbstractController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @PostMapping("/incluir")
    @Operation(description = "Método utilizado para realizar a inclusão de um genero", responses = {
            @ApiResponse(responseCode = "200", description = "Genero Incluído", content = @Content(mediaType = "application/json"))})
    public UsuarioDto incluir(@RequestBody UsuarioDto usuarioDto) {
        UsuarioModel generoModel = usuarioMapper.toEntity(usuarioDto);
        usuarioService.salvar(generoModel);
        return usuarioMapper.toDTO(generoModel);
    }
}
