package br.ueg.musica.controller;

import br.ueg.musica.dto.MusicaDto;
import br.ueg.musica.mapper.MusicaMapper;
import br.ueg.musica.model.MusicaModel;
import br.ueg.musica.service.MusicaService;
import br.ueg.musica.service.impl.MusicaServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "")
public class MusicaController {

    @Autowired
    private MusicaService musicaService;

    @Autowired
    private MusicaMapper musicaMapper;

    @PostMapping("/incluir")
    public String incluir(@RequestBody MusicaDto musicaDto) {
        MusicaModel musicaModel = musicaMapper.toModelo(musicaDto);
        musicaService.incluir(musicaModel);
        return musicaDto.toString();
    }

    @PutMapping(path = "/alterar/{id}")
    public MusicaDto alterar(@RequestBody() MusicaDto musicaDto, @PathVariable(name = "id") Long idMusica) {
        MusicaModel musicaModel = musicaMapper.toModelo(musicaDto);
        musicaModel.setId(idMusica);
        MusicaModel alterar = musicaService.alterar(musicaModel);
        return musicaMapper.toMusicaDto(alterar);
    }

    @DeleteMapping(path = "/excluir/{id}")
    public MusicaDto excluir(@PathVariable(name = "id") Long idMusica) {
        MusicaModel musicaModel = this.musicaService.excluir(idMusica);
        return musicaMapper.toMusicaDto(musicaModel);
    }

    @GetMapping()
    public List<MusicaDto> listAll() {
        List<MusicaModel> musicas = musicaService.listar();
        return musicaMapper.toListDto(musicas);
    }
}