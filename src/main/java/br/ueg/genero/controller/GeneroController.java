package br.ueg.genero.controller;


import br.ueg.genero.dto.GeneroDto;
import br.ueg.genero.mapper.GeneroMapper;
import br.ueg.genero.model.GeneroModel;
import br.ueg.genero.service.GeneroService;
import br.ueg.musica.model.MusicaModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path = "${app.api.base}/genero")
public class GeneroController {

    @Autowired
    private GeneroMapper generoMapper;

    @Autowired
    private GeneroService generoService;

    @PostMapping("/incluir")
    @Operation(description = "Método utilizado para realizar a inclusão de um genero", responses = {
            @ApiResponse(responseCode = "200", description = "Genero Incluído", content = @Content(mediaType = "application/json"))})
    public GeneroDto incluir(@RequestBody GeneroDto generoDto) {
        GeneroModel generoModel = generoMapper.toModelo(generoDto);
        generoService.incluir(generoModel);
        return generoMapper.toGeneroDto(generoModel);
    }

    @PutMapping(path = "/alterar/{id}")
    @Operation(description = "Método utilizado para alterar os dados de um genero", responses = {
            @ApiResponse(responseCode = "200", description = "Genero Alterada", content = @Content(mediaType = "application/json"))})
    public GeneroDto alterar(@RequestBody() GeneroDto generoDto, @PathVariable(name = "id") Long idGenero) {
        GeneroModel generoModel = generoMapper.toModelo(generoDto);
        generoModel.setId(idGenero);
        GeneroModel alterar = generoService.alterar(generoModel);
        return generoMapper.toGeneroDto(alterar);
    }

    @DeleteMapping(path = "/excluir/{id}")
    @Operation(description = "Método utilizado para remover um genero pelo id informado", responses = {
            @ApiResponse(responseCode = "200", description = "Genero Removida", content = @Content(mediaType = "application/json"))})
    public GeneroDto excluir(@PathVariable(name = "id") Long idGenero) {
        GeneroModel generoModel = this.generoService.excluir(idGenero);
        return generoMapper.toGeneroDto(generoModel);
    }

    @GetMapping(path = "/listar")
    @Operation(description = "Listagem Geral", responses = {
            @ApiResponse(responseCode = "200", description = "Listagem geral", content = @Content(mediaType = "application/json"))})
    public List<GeneroDto> listAll() {
        List<GeneroModel> musicas = generoService.listar();
        return generoMapper.toListDto(musicas);
    }

    @GetMapping(path = "/getbyID/{id}")
    @Operation(description = "Método utilizado para buscar um genero pelo id informado", responses = {
            @ApiResponse(responseCode = "200", description = "Buscar genero", content = @Content(mediaType = "application/json"))})
    public GeneroDto buscarPorId(@PathVariable(name = "id") Long idGenero){
        GeneroModel generoModel = generoService.pesquisarGeneroOuGeraErro(idGenero);
        return generoMapper.toGeneroDto(generoModel);
    }


}
