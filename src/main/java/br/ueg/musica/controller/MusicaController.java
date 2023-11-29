package br.ueg.musica.controller;

import br.ueg.musica.dto.MusicaDto;
import br.ueg.musica.dto.MusicasFavoritasGeneroDto;
import br.ueg.musica.mapper.MusicaMapper;
import br.ueg.musica.model.MusicaModel;
import br.ueg.musica.service.MusicaService;
import br.ueg.musica.service.impl.MusicaServiceImpl;
import br.ueg.prog.webi.api.controller.CrudController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "${app.api.base}/musica")
public class MusicaController extends CrudController
    <MusicaModel, MusicaDto, Long, MusicaMapper, MusicaService>
{

    @GetMapping(path = "/favoritar/{id}")
    @Operation(description = "Método utilizado para favoritar uma música pelo id informado", responses = {
            @ApiResponse(responseCode = "200", description = "Favoritar música", content = @Content(mediaType = "application/json"))})
    public MusicaDto favoritarMusica (@PathVariable(name = "id") Long idMusica){
        MusicaModel musicaModel = service.favoritarMusica(idMusica);
        return mapper.toDTO(musicaModel);
    }

    @GetMapping(path = "/listar favoritas")
    @Operation(description = "Listagem Favoritas", responses = {
            @ApiResponse(responseCode = "200", description = "Listagem favoritas", content = @Content(mediaType = "application/json"))})
    public List<MusicasFavoritasGeneroDto> listarfavoritas() {
        return service.listarFavoritas();
    }

}