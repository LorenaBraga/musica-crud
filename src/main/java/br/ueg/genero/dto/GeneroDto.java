package br.ueg.genero.dto;

import br.ueg.musica.dto.MusicaDto;
import lombok.Data;

import java.util.List;

@Data
public class GeneroDto {
    private Long id;
    private String nome;
    private List<MusicaDto> musicas;
}
