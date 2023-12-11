package br.ueg.musica.controller;

import br.ueg.musica.dto.MusicaDto;
import br.ueg.musica.dto.MusicasFavoritasGeneroDto;
import br.ueg.musica.mapper.MusicaMapper;
import br.ueg.musica.model.Musica;
import br.ueg.musica.service.MusicaService;
import br.ueg.prog.webi.api.controller.CrudController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "${app.api.base}/musica")
@PreAuthorize(value = "isAuthenticated()")
public class MusicaController extends CrudController
    <Musica, MusicaDto, Long, MusicaMapper, MusicaService>
{

    @GetMapping(path = "/favoritar/{id}")
    @Operation(description = "Método utilizado para favoritar uma música pelo id informado", responses = {
            @ApiResponse(responseCode = "200", description = "Favoritar música", content = @Content(mediaType = "application/json"))})
    public MusicaDto favoritarMusica (@PathVariable(name = "id") Long idMusica){
        Musica musicaModel = service.favoritarMusica(idMusica);
        return mapper.toDTO(musicaModel);
    }

    @GetMapping(path = "/listar favoritas")
    @Operation(description = "Listagem Favoritas", responses = {
            @ApiResponse(responseCode = "200", description = "Listagem favoritas", content = @Content(mediaType = "application/json"))})
    public List<MusicasFavoritasGeneroDto> listarfavoritas() {
        return service.listarFavoritas();
    }

}