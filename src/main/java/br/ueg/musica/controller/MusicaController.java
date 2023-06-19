package br.ueg.musica.controller;

import br.ueg.musica.dto.MusicaDto;
import br.ueg.musica.mapper.MusicaMapper;
import br.ueg.musica.model.MusicaModel;
import br.ueg.musica.service.MusicaService;
import br.ueg.musica.service.impl.MusicaServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "${app.api.base}")
public class MusicaController {

    @Autowired
    private MusicaService musicaService;

    @Autowired
    private MusicaMapper musicaMapper;

    @PostMapping("/incluir")
    @Operation(description = "Método utilizado para realizar a inclusão de uma música", responses = {
            @ApiResponse(responseCode = "200", description = "Música Incluída", content = @Content(mediaType = "application/json"))})
    public MusicaDto incluir(@RequestBody MusicaDto musicaDto) {
        MusicaModel musicaModel = musicaMapper.toModelo(musicaDto);
        musicaService.incluir(musicaModel);
        return musicaMapper.toMusicaDto(musicaModel);
    }

    @PutMapping(path = "/alterar/{id}")
    @Operation(description = "Método utilizado para alterar os dados de uma música", responses = {
            @ApiResponse(responseCode = "200", description = "Música Alterada", content = @Content(mediaType = "application/json"))})
    public MusicaDto alterar(@RequestBody() MusicaDto musicaDto, @PathVariable(name = "id") Long idMusica) {
        MusicaModel musicaModel = musicaMapper.toModelo(musicaDto);
        musicaModel.setId(idMusica);
        MusicaModel alterar = musicaService.alterar(musicaModel);
        return musicaMapper.toMusicaDto(alterar);
    }

    @DeleteMapping(path = "/excluir/{id}")
    @Operation(description = "Método utilizado para remover uma música pelo id informado", responses = {
            @ApiResponse(responseCode = "200", description = "Música Removida", content = @Content(mediaType = "application/json"))})
    public MusicaDto excluir(@PathVariable(name = "id") Long idMusica) {
        MusicaModel musicaModel = this.musicaService.excluir(idMusica);
        return musicaMapper.toMusicaDto(musicaModel);
    }

    @GetMapping(path = "/listar")
    @Operation(description = "Listagem Geral", responses = {
            @ApiResponse(responseCode = "200", description = "Listagem geral", content = @Content(mediaType = "application/json"))})
    public List<MusicaDto> listAll() {
        List<MusicaModel> musicas = musicaService.listar();
        return musicaMapper.toListDto(musicas);
    }

    @GetMapping(path = "/getbyID/{id}")
    @Operation(description = "Método utilizado para buscar uma música pelo id informado", responses = {
            @ApiResponse(responseCode = "200", description = "Buscar música", content = @Content(mediaType = "application/json"))})
    public MusicaDto buscarPorId(@PathVariable(name = "id") Long idMusica){
        MusicaModel musicaModel = musicaService.pesquisarMusicaOuGeraErro(idMusica);
        return musicaMapper.toMusicaDto(musicaModel);
    }

    @GetMapping(path = "/favoritar/{id}")
    @Operation(description = "Método utilizado para favoritar uma música pelo id informado", responses = {
            @ApiResponse(responseCode = "200", description = "Favoritar música", content = @Content(mediaType = "application/json"))})
    public MusicaDto favoritarMusica (@PathVariable(name = "id") Long idMusica){
        MusicaModel musicaModel = musicaService.favoritarMusica(idMusica);
        return musicaMapper.toMusicaDto(musicaModel);
    }
}