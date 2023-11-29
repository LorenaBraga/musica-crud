package br.ueg.musica.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GeneroDto {
    private Long id;
    private String nome;
    private List<MusicaDto> musicas;
}
